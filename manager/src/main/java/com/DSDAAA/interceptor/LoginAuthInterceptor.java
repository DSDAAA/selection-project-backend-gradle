package com.DSDAAA.interceptor;

import cn.hutool.core.util.StrUtil;
import com.DSDAAA.AuthContextUtil;
import com.DSDAAA.constant.CacheConstant;
import com.DSDAAA.entity.system.SysUser;
import com.DSDAAA.vo.common.Result;
import com.DSDAAA.vo.common.ResultCodeEnum;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录验证拦截器：
 *      每次请求，都需要拦截请求，获取请求token，判断token是否有效：
 *          失效：重新登录
 *          有效：更加token令牌从redis中获取用户信息，与当前线程进行绑定进行数据共享，重置token时间。
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 前端发起请求，到达后端，拦截进行拦截。
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //int i = 1/0;
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        //0.如果是OPTIONS跨域预检请求，直接放行
        String method = request.getMethod();
        if("OPTIONS".equals(method)){
            return  true;
        }

        //1.获取请求令牌
        String token = request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            //令牌为空，响应错误结果
            responseNoLoginInfo(response) ;
            return false;
        }

        //2.判断令牌是否有效
        String sysUserJsonString = redisTemplate.opsForValue().get(CacheConstant.USER_LOGIN_PREFIX + token);
        if(StrUtil.isEmpty(sysUserJsonString)){
            //令牌失效（30分钟已过），响应错误结果
            responseNoLoginInfo(response) ;
            return false;
        }

        //3.获取用户信息
        SysUser sysUser = JSON.parseObject(sysUserJsonString, SysUser.class);

        //4.将用户信息与当前线程绑定
        AuthContextUtil.set(sysUser);

        //5.重置redis用户令牌时间
        // TODO  临时代码，上线需要改回来
        //redisTemplate.expire(CacheConstant.USER_LOGIN_PREFIX + token,30, TimeUnit.MINUTES);



        //6.放行
        return true;
    }

    private void responseNoLoginInfo(HttpServletResponse response) {
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * 后端处理请求之后（执行过controller-service-dao），返回给前端结果时进行拦截：清理线程绑定数据
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handlernthat started asynchronous
     * execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     * (can also be {@code null})
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AuthContextUtil.remove();
    }
}

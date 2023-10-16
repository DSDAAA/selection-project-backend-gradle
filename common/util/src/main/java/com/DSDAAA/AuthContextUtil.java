package com.DSDAAA;


import com.DSDAAA.entity.system.SysUser;

/**
 * 自定义封装ThreadLocal工具类，借助于TheadLocal将数据与当前线程进行绑定。用途：在线程上进行数据共享
 * 注意：
 * 与线程绑定数据时，每次都是一个新数据，否则，同一个数据绑定多个线程上，就会出现资源共享问题。
 */
public class AuthContextUtil {
    // 创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    // 定义存储数据的静态方法
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    // 定义获取数据的方法
    public static SysUser get() {
        return threadLocal.get();
    }

    // 删除数据的方法
    public static void remove() {
        threadLocal.remove();
    }

}

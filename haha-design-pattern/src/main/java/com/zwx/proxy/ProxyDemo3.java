package com.zwx.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理模式 cglib
 *
 * @author 张文旭
 * @date 2021/01/14 22:47
 **/
public class ProxyDemo3 {
    public static void main(String[] args) {
        IMenuDao target = new MenuDao();
        IMenuDao proxy = (IMenuDao) new MenuProxy(target).getProxyInstance();
        proxy.query();
        System.out.println();
        proxy.save();
    }
}


interface IMenuDao {
    void save();

    void query();
}

class MenuDao implements IMenuDao {

    @Override
    public void save() {
        System.out.println("我是save方法");
    }

    @Override
    public void query() {
        System.out.println("我是query方法");
    }
}

class MenuProxy implements MethodInterceptor {
    private Object target;

    public MenuProxy(Object target) {
        this.target = target;
    }

    //为目标对象生成代理对象
    public Object getProxyInstance() {
        //工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类对象代理
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开启事务");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, args);
        System.out.println("关闭事务");
        return returnValue;
    }
}

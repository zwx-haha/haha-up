package com.zwx.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 张文旭
 * <p>
 * JDK 自带基于接口的动态代理
 * @date 2021/01/14 22:20
 **/
public class ProxyDemo2 {
    public static void main(String[] args) {
        IUserDao target = new UserDaoImpl();
        System.out.println(target.getClass());  //输出目标对象信息
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());  //输出代理对象信息
        proxy.save();  //执行代理方法
        proxy.query();
    }
}

interface IUserDao {
    void save();

    void query();
}

class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("我是保存用户方法");
    }

    @Override
    public void query() {
        System.out.println("我是query用户方法");
    }
}

class ProxyFactory {
    private Object target;// 维护一个目标对象

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");

                        // 执行目标对象方法
                        Object returnValue = method.invoke(target, args);

                        System.out.println("提交事务");
                        return null;
                    }
                });
    }

}




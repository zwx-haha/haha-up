package com.zwx.singleton;

/**
 * 单利模式1<br/>
 * 此模式为饿汉模式，不论是否使用都会创建一个实例对象出来，没有线程安全的问题
 *
 * @author 张文旭
 * @date 2021/01/11 15:12
 */
public class SingletonDemo1 {

    private static final SingletonDemo1 INSTANCE = new SingletonDemo1();

    private SingletonDemo1() {
    }

    public static SingletonDemo1 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(
                    () -> {
                        System.out.println(SingletonDemo1.getInstance().hashCode());
                    })
                    .start();
        }
    }
}

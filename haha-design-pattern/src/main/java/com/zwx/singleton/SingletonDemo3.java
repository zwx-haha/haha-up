package com.zwx.singleton;

/**
 * 单例模式3
 * 完全保证线程安全，效率低下,每次都是获取锁
 *
 * @author 张文旭
 * @date 2021/01/11 15:38
 **/
public class SingletonDemo3 {
    private static SingletonDemo3 INSTANCE;

    private SingletonDemo3() {
    }

    public static synchronized SingletonDemo3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonDemo3();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo3.getInstance().hashCode());
            }).start();
        }
    }
}

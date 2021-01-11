package com.zwx.singleton;

/**
 * 单例模式5
 * 饿汉模式
 *
 * @author 张文旭
 * @date 2021/01/11 15:53
 **/
public class SingletonDemo5 {
    private final static SingletonDemo5 INSTAMCE;

    static {
        INSTAMCE = new SingletonDemo5();
    }

    private SingletonDemo5() {
    }

    public static SingletonDemo5 getInstance() {
        return INSTAMCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(
                    () -> {
                        System.out.println(SingletonDemo5.getInstance().hashCode());
                    })
                    .start();
        }
    }
}

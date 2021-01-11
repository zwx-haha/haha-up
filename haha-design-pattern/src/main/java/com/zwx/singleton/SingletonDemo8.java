package com.zwx.singleton;

/**
 * 单例模式8
 *
 * @author 张文旭
 * @date 2021/01/11 16:02
 **/
public enum SingletonDemo8 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(
                    () -> {
                        System.out.println(SingletonDemo8.INSTANCE.hashCode());
                    })
                    .start();
        }
    }
}

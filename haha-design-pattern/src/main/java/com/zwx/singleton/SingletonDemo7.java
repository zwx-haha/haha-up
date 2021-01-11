package com.zwx.singleton;

/**
 * 单例模式内部类
 *
 * @author 张文旭
 * @date 2021/01/11 15:59
 **/
public class SingletonDemo7 {

    private SingletonDemo7() {
    }

    private static class SingletonDemo7Holder {
        private static final SingletonDemo7 INSTANCE = new SingletonDemo7();
    }

    public static SingletonDemo7 getInstance() {
        return SingletonDemo7Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo7.getInstance().hashCode());
            }).start();
        }
    }
}

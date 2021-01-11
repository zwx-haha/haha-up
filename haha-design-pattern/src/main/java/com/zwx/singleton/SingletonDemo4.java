package com.zwx.singleton;

/**
 * 单例模式4 细化锁粒度，仍然线程不安全
 *
 * @author 张文旭
 * @date 2021/01/11 15:41
 */
public class SingletonDemo4 {
  private static SingletonDemo4 INSTANCE;

  private SingletonDemo4() {
  }

  public static SingletonDemo4 getInstance() {
    if (INSTANCE == null) {
      synchronized (SingletonDemo4.class) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        INSTANCE = new SingletonDemo4();
      }
    }
    return INSTANCE;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(
              () -> {
                System.out.println(SingletonDemo4.getInstance().hashCode());
              })
              .start();
    }
  }
}

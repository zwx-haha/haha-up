package com.zwx.singleton;

/**
 * 单例模式6
 *
 * @author 张文旭
 * @date 2021/01/11 15:56
 */
public class SingletonDemo6 {
  private static volatile SingletonDemo6 INSTANCE;

  private SingletonDemo6() {
  }

  public static SingletonDemo6 getInstance() {
    if (INSTANCE == null) {
      synchronized (SingletonDemo6.class) {
        if (INSTANCE == null) {
          INSTANCE = new SingletonDemo6();
        }
      }
    }
    return INSTANCE;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(
              () -> {
                System.out.println(SingletonDemo6.getInstance().hashCode());
              })
              .start();
    }
  }
}

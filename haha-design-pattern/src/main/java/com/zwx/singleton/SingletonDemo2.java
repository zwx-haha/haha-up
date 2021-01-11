package com.zwx.singleton;

/**
 * 单利模式2 懒加载模式
 * 出现线程不安全的问题
 *
 * @author 张文旭
 * @date 2021/01/11 15:17
 */
public class SingletonDemo2 {

  private static SingletonDemo2 INSTANCE;

  private SingletonDemo2() {
  }

  public static SingletonDemo2 getInstance() {
    if (INSTANCE == null) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      INSTANCE = new SingletonDemo2();
    }
    return INSTANCE;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(
              () -> {
                System.out.println(SingletonDemo2.getInstance().hashCode());
              })
              .start();
    }
  }
}

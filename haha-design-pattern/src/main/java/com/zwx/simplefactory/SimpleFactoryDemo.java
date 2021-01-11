package com.zwx.simplefactory;

/**
 * 简单工厂模式案例
 *
 * @author 张文旭
 * @date 2021/01/11 16:52
 **/
public class SimpleFactoryDemo {

    interface Product {
        void show();
    }

    static class ProductA implements Product {
        @Override
        public void show() {
            System.out.println("我是产品A");
        }
    }

    static class ProductB implements Product {
        @Override
        public void show() {
            System.out.println("我是产品B");
        }
    }

    final class Const {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
        static final int PRODUCT_C = 2;
    }

    static class SimpleFactory {
        public static Product makeProduct(int kind) {
            switch (kind) {
                case Const.PRODUCT_A:
                    return new ProductA();
                case Const.PRODUCT_B:
                    return new ProductB();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Product product = SimpleFactory.makeProduct(0);
        product.show();
    }
}

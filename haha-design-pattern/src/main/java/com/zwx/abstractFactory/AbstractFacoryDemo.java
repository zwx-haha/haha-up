package com.zwx.abstractFactory;

/**
 * 抽象工厂demo
 *
 * @author 张文旭
 * @date 2021/01/11 17:08
 **/
public class AbstractFacoryDemo {

    interface Animal {
        void show();
    }

    interface Plant {
        void show();
    }

    static class Dog implements Animal {
        @Override
        public void show() {
            System.out.println("我是dog");
        }
    }

    static class Cat implements Animal {
        @Override
        public void show() {
            System.out.println("我是cat");
        }
    }

    static class Apple implements Plant {
        @Override
        public void show() {
            System.out.println("我是Apple");
        }
    }

    interface AbstractFactory {
        Animal newAnimal();

        Plant newPlant();
    }

    static class HenanFactory implements AbstractFactory {
        @Override
        public Animal newAnimal() {
            return new Dog();
        }

        @Override
        public Plant newPlant() {
            return new Apple();
        }
    }

    static class HebeiFactory implements AbstractFactory {
        @Override
        public Animal newAnimal() {
            return new Cat();
        }

        @Override
        public Plant newPlant() {
            return new Apple();
        }
    }

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new HenanFactory();
        Animal animal = abstractFactory.newAnimal();
        Plant plant = abstractFactory.newPlant();
        System.out.println("HenanFactory 生产的动物和植物 --> ");
        animal.show();
        plant.show();
        abstractFactory = new HebeiFactory();
        animal = abstractFactory.newAnimal();
        plant = abstractFactory.newPlant();
        System.out.println("HebeiFactory 生产的动物和植物 --> ");
        animal.show();
        plant.show();
    }
}

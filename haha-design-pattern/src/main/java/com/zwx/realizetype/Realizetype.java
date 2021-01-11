package com.zwx.realizetype;

/**
 * 原型模式案例
 *
 * @author 张文旭
 * @date 2021/01/11 16:07
 **/
public class Realizetype implements Cloneable {

    Realizetype() {
        System.out.println("具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype r1 = new Realizetype();
        Realizetype r2 = (Realizetype) r1.clone();

        System.out.println("原型模式copy结果-> " + (r1 == r2));
        System.out.println("原型模式copy结果-> " + r1.hashCode() + "      " + r2.hashCode());
    }
}

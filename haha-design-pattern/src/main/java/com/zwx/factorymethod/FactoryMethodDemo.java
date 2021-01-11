package com.zwx.factorymethod;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Constructor;

/**
 * 工厂方法模式demo
 *
 * @author 张文旭
 * @date 2021/01/11 16:37
 */
public class FactoryMethodDemo {

    interface Product {
        void show();
    }

    class Product1 implements Product {
        @Override
        public void show() {
            System.out.println("我是产品1");
        }
    }

    class Product2 implements Product {
        @Override
        public void show() {
            System.out.println("我是产品2");
        }
    }

    interface AbstractFactory {
        Product newProduct();
    }

    class ProductFactory1 implements AbstractFactory {
        @Override
        public Product newProduct() {
            return new Product1();
        }
    }

    class ProductFactory2 implements AbstractFactory {
        @Override
        public Product newProduct() {
            return new Product2();
        }
    }

    static class ReadXML1 {
        // 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
        public static Object getObject() {
            try {
                // 创建文档对象
                DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dFactory.newDocumentBuilder();
                Document doc;
                doc = builder.parse(new File("haha-design-pattern/src/FactoryMethod/config1.xml"));
                // 获取包含类名的文本节点
                NodeList nl = doc.getElementsByTagName("className");
                Node classNode = nl.item(0).getFirstChild();
                String cName = "com.zwx.factorymethod.FactoryMethodDemo$" + classNode.getNodeValue();
                System.out.println("新类名：" + cName);
                // 通过类名生成实例对象并将其返回
                Class<?> c = Class.forName(cName);
                FactoryMethodDemo ot = new FactoryMethodDemo();
                Constructor<?> constructor = c.getDeclaredConstructor(FactoryMethodDemo.class);
                Object obj = constructor.newInstance(ot);
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static void main(String[] args) {
            // 生产产品
            Product product1 = null;
            AbstractFactory ab;
            ab = (AbstractFactory) ReadXML1.getObject();
            product1 = ab.newProduct();
            product1.show();
        }
    }
}

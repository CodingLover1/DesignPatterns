package com.ws.FactroyPattern;

interface  IProduct{

}

class Product1 implements  IProduct{
    public Product1(){
        System.out.println("创建了1型号产品");
    }
}

class Product2 implements  IProduct{
    public Product2(){
        System.out.println("创建了2型号产品");

    }
}
public class ProductFactory {
    public static IProduct createProduct(String productNo){
        switch (productNo){
            case "1": return new Product1();
            case "2": return new Product2();
            default: return new Product1();
        }
    }

    public static void main(String[] args){
        IProduct product=ProductFactory.createProduct("1");

    }
}

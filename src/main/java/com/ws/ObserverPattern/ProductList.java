package com.ws.ObserverPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable {
    public static ProductList instance=null;
    public static ProductList getInstance(){
        if(instance==null){
            synchronized (ProductList.class){
                if(instance==null){
                    instance=new ProductList();
                    instance.productList=new ArrayList<String>();
                }
            }
        }
        return instance;
    }

    private ProductList(){}

    private List<String> productList=null; //产品列表

    /**
     * 添加观察者
     * @Param Observer 观察者
     */

     public void addProductListObserver(Observer observer){
         this.addObserver(observer);
     }

    /**
     * 添加产品
     * @param newProduct
     */
    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品:"+newProduct);
        this.setChanged();//设置被观察对象发生变化
        this.notifyObservers(newProduct);//通知观察者，并传递新产品
     }
}

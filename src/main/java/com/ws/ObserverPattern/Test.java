package com.ws.ObserverPattern;

import javax.sql.rowset.JdbcRowSet;

public class Test {
    public static void main(String[] args){
        ProductList productList=ProductList.getInstance();

        JingDongObserver observer=new JingDongObserver();

        productList.addProductListObserver(observer);

        productList.addProduct("笔记本电脑");


    }
}

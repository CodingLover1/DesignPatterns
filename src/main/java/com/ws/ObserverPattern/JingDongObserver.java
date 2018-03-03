package com.ws.ObserverPattern;

import java.util.Observable;
import java.util.Observer;

public class JingDongObserver implements Observer {

    @Override
    public void update(Observable o,Object product){
        String newProduct=(String)product;
        System.out.println("发布新产品["+newProduct+"]到京东电商接口");
    }
}

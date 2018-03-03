package com.ws.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {
    //真实对象
    private Object target=null;

    /**
     * 建立代理对象和真实对象的代理关系，并返回代理对象
     * @param target 真是对象
     * @return 代理对象
     */
    public Object bind(Object target){
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * 代理方法的逻辑
     * @param proxy 代理对象
     * @param method
     * @param args
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method,Object[] args)throws Throwable{
        System.out.println("进入代理对象的逻辑方法");
        System.out.println("在调度真实对象之前的服务");
        Object obj=method.invoke(target,args);
        System.out.println("在调度真实对象之后的服务");
        return obj;
    }

    public static void main(String[] args){
        JdkProxyExample jdkProxyExample=new JdkProxyExample();
        HelloWorld helloWorld=(HelloWorld)jdkProxyExample.bind(new HelloWorldImpl());
        helloWorld.sayHelloWorld();
    }
}

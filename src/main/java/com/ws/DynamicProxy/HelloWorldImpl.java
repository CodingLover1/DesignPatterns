package com.ws.DynamicProxy;

interface HelloWorld{
    public void sayHelloWorld();
}
public class HelloWorldImpl implements HelloWorld{
    @Override
    public  void sayHelloWorld(){
        System.out.println("Hello World!");
    }
}


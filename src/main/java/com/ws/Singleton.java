package com.ws;

public class Singleton{
    public static Singleton instance=null;
    public static Singleton getInstance(){
        return SingletonFactory.getInstance();
    }
    
    private Singleton(){}
    
    private static class SingletonFactory{
        private static  Singleton singleton=new Singleton();
        public static Singleton getInstance(){
            return singleton;
        }
    }

}

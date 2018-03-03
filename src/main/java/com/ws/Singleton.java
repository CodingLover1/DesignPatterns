package com.ws;

public class Singleton {
    public static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class){
                if(instance==null){ //这一步要进行判断，是因为在多线程环境下，其他的线程也有可能进入第一个if中
                    instance=new Singleton();
                }
            }
        }
        return instance;

    }

    private Singleton() {
    }
}
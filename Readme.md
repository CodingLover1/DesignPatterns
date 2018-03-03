### Java 设计模式

#### JDK动态代理
> jdk动态代理是java.lang.reflect.*包提供的方式，它必须借助一个接口才能
> 产生代理对象，通过动态代理技术，我们可以在调用一个对象的某个函数之前
> 插入一个新的服务，或者在之后插入一个新的服务,即它可以通过在不该变一个类
> 的源代码的情况下，为该类添加新的函数功能。

```java
interface HelloWorld{
    public void sayHelloWorld();
}
public class HelloWorldImpl implements HelloWorld{
    @Override
    public  void sayHelloWorld(){
        System.out.println("Hello World!");
    }
}
```

```java
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

```




#### 单例模式 (DLC双锁检查机制，线程安全)
```java
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
```

#### 观察者模式
> 观察者模式是一种发布订阅模式
* 被观察者需要继承 java.util.Observable类
* 观察者需要实现Observer接口

被观察者
```java
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
```

观察者
```java

public class JingDongObserver implements Observer {

    @Override
    public void update(Observable o,Object product){
        String newProduct=(String)product;
        System.out.println("发布新产品["+newProduct+"]到京东电商接口");
    }
}

```

#### 工厂模式和抽象工厂模式
> 工厂模式：一个工厂可能生产不同型号的产品，那么每种产品都有一个类，用户在
> 工厂买该产品只要提供一个型号描述，就可以得到该工厂的指定型号的产品。

```java
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
```



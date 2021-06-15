package java.lang;

/**
 * 双亲委派机制,导致bootstrapClassLoader加载了系统类库String,我们自定义的String没加载,好处:防止核心api被篡改和类的重复加载
 */

public class String {
    //
    static{
        System.out.println("我是自定义的String类的静态代码块");
    }
    //错误: 在类 java.lang.java.lang.String 中找不到 main 方法,核心类库的String类没有main方法
    public static void main(String[] args) {
        System.out.println("hello,java.lang.String");
    }
}


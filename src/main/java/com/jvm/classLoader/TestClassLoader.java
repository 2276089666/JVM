package com.jvm.classLoader;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

/**
 * @Author ws
 * @Date 2021/6/10 15:13
 */
public class TestClassLoader {
    public static void main(String[] args) {
        ClassLoader AppClassLoader = ClassLoader.getSystemClassLoader(); // AppClassLoader
        System.out.println(AppClassLoader);

        ClassLoader extClassLoader = AppClassLoader.getParent();  // ExtClassLoader
        System.out.println(extClassLoader);

        // 拿不到,用C++写的,用来加载String等核心类库
        ClassLoader bootstrap = extClassLoader.getParent(); // null
        System.out.println(bootstrap);


        ClassLoader classLoader = TestClassLoader.class.getClassLoader();  // AppClassLoader
        System.out.println(classLoader);

        ClassLoader bootstrapClassLoader = String.class.getClassLoader();  // null
        System.out.println(bootstrapClassLoader);


        // 看看Bootstrap能加载的路径
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        for (URL url : bootstrapClassPath.getURLs()) {
            System.out.println(url.toExternalForm());
        }
        /**
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/resources.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/rt.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/sunrsasign.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jsse.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jce.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/charsets.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jfr.jar
         * file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/classes
         */

        // 扩展加载器能加载的路径
        System.out.println("===============");
        String ext = System.getProperty("java.ext.dirs");
        String[] split = ext.split(";");
        for (String s : split) {
            System.out.println(s);
        }
        /**
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext
         * C:\WINDOWS\Sun\Java\lib\ext
         */

    }
}

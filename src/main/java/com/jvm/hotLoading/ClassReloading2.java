package com.jvm.hotLoading;

import java.io.*;

public class ClassReloading2 {
    // 重写ClassLoader的loadClass方法打破双亲委派实现热替换,热部署
    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            File f = null;
            InputStream is = null;
            try {
                f = new File("E:/JVM/target/classes/" + name.replace(".", "/").concat(".class"));
                // 没有之前的find in cache
                // First, check if the class has already been loaded
                // Class<?> c = findLoadedClass(name);
                if (!f.exists()) {
                    return super.loadClass(name);
                }

                is = new FileInputStream(f);
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ClassFormatError classFormatError) {
                classFormatError.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws Exception {
        MyLoader m = new MyLoader();
        Class clazz = m.loadClass("com.jvm.classLoader.Test");

        // 上面的MyLoader被替换,没有引用指向它,就会被回收实现热替换
        m = new MyLoader();
        Class clazzNew = m.loadClass("com.jvm.classLoader.Test");

        // 最终前后对象不一样,说明我们的类再次被加载进来,可以实现热部署,热替换
        System.out.println(clazz == clazzNew);
    }
}

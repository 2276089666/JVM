package com.jvm.classLoader;


import java.io.*;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("E:/testClassLoader/", name.replace(".", "/").concat(".class"));
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(f);
            baos = new ByteArrayOutputStream();

            int b = 0;
            while ((b = fis.read()) != 0) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();

            // 将字节数组转换为类Class的实例。在Class可以使用之前,它必须被解析
            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //throws ClassNotFoundException
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        ClassLoader l = new MyClassLoader();
        Class clazz = l.loadClass("com.jvm.classLoader.Test");
        Class clazz1 = l.loadClass("com.jvm.classLoader.Test");

        System.out.println(clazz == clazz1);

        Test o = (Test) clazz.newInstance();
        o.m();

        System.out.println(getSystemClassLoader());        // AppClassLoader
        System.out.println(l.getClass().getClassLoader());  // AppClassLoader
        System.out.println(l.getParent());                // AppClassLoader

    }
}

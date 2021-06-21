package com.jvm.classLoader;


import java.io.*;

public class ClassLoaderWithEncryption extends ClassLoader {

    // 密钥
    public static int seed = 0B10110110;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            File f = new File("E:/testClassLoader/", name.replace('.', '/').concat(".md5class"));
            fis = new FileInputStream(f);
            baos = new ByteArrayOutputStream();

            int b = 0;
            while ((b = fis.read()) != 0) {
                // 再次异或解密
                baos.write(b ^ seed);
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


    private static void encFile(String name) throws Exception {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            File f = new File("E:/testClassLoader/", name.replace('.', '/').concat(".class"));
            fis = new FileInputStream(f);
            File md5File = new File("E:/testClassLoader/", name.replace(".", "/").concat(".md5class"));
            fos = new FileOutputStream(md5File);
            int b = 0;

            while ((b = fis.read()) != -1) {
                // 使用异或加密,异或两次解密
                fos.write(b ^ seed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
    }

    public static void main(String[] args) throws Exception {

        // 加密我们的class文件,生成md5class的加密文件
        encFile("com.jvm.classLoader.Test");

        ClassLoader l = new ClassLoaderWithEncryption();
        Class clazz = l.loadClass("com.jvm.classLoader.Test");

        Test t = (Test) clazz.newInstance();
        t.m();

        System.out.println(l.getClass().getClassLoader());  // AppClassLoader
        System.out.println(l.getParent());                  // AppClassLoader
    }
}

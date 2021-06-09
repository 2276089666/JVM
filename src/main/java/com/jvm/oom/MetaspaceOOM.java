package com.jvm.oom;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author ws
 * @Date 2021/6/9 12:28
 */
//  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m

/**
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 */
public class MetaspaceOOM extends ClassLoader {
    public static void main(String[] args) {
        for (; ; ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(ClassA.class);
            enhancer.setUseCache(false); // 重要
            enhancer.setCallback(
                    new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                           return methodProxy.invokeSuper(o, objects);
                        }
                    }
            );
            enhancer.create();
        }
    }

}

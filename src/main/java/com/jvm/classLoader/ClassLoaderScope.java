package com.jvm.classLoader;

public class ClassLoaderScope {
    public static void main(String[] args) {
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));
        /**
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\resources.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\sunrsasign.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jsse.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jce.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\charsets.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jfr.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\classes
         */

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));
        /**
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext
         * C:\WINDOWS\Sun\Java\lib\ext
         */


        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
        /**
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\charsets.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\deploy.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\access-bridge-64.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\cldrdata.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\dnsns.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\jaccess.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\jfxrt.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\localedata.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\nashorn.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunec.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunjce_provider.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunmscapi.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\sunpkcs11.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext\zipfs.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\javaws.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jce.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jfr.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jfxswt.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\jsse.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\management-agent.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\plugin.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\resources.jar
         * C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar
         * E:\JVM\target\classes
         * C:\Users\22760\.m2\repository\org\openjdk\jol\jol-core\0.9\jol-core-0.9.jar
         * C:\Users\22760\.m2\repository\cglib\cglib\3.2.12\cglib-3.2.12.jar
         * C:\Users\22760\.m2\repository\org\ow2\asm\asm\7.1\asm-7.1.jar
         * C:\Program Files\JetBrains\IntelliJ IDEA 2020.1\lib\idea_rt.jar
         */
    }
}

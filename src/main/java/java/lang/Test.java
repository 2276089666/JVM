package java.lang;

public class Test {

    // 报错，不允许在java.lang包下建项目

    /**
     *
     * java.lang.SecurityException: Prohibited package name: java.lang
     */
    public static void main(String[] args) {
        System.out.println("hello!");
    }
}

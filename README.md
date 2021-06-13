# JVM学习笔记

## 1.ClassFileFormat

### 1.1.编译字节码

使用sublime打开我们的 TestClass.class文件

```hex
cafe babe 0000 0034 0016 0a00 0400 1209
0003 0013 0700 1407 0015 0100 016d 0100
0149 0100 063c 696e 6974 3e01 0003 2829
5601 0004 436f 6465 0100 0f4c 696e 654e
756d 6265 7254 6162 6c65 0100 124c 6f63
616c 5661 7269 6162 6c65 5461 626c 6501
0004 7468 6973 0100 234c 636f 6d2f 6a76
6d2f 636c 6173 7346 696c 6546 6f72 6d61
742f 5465 7374 436c 6173 733b 0100 0369
6e63 0100 0328 2949 0100 0a53 6f75 7263
6546 696c 6501 000e 5465 7374 436c 6173
732e 6a61 7661 0c00 0700 080c 0005 0006
0100 2163 6f6d 2f6a 766d 2f63 6c61 7373
4669 6c65 466f 726d 6174 2f54 6573 7443
6c61 7373 0100 106a 6176 612f 6c61 6e67
2f4f 626a 6563 7400 2100 0300 0400 0000
0100 0200 0500 0600 0000 0200 0100 0700
0800 0100 0900 0000 2f00 0100 0100 0000
052a b700 01b1 0000 0002 000a 0000 0006
0001 0000 0007 000b 0000 000c 0001 0000
0005 000c 000d 0000 0001 000e 000f 0001
0009 0000 0031 0002 0001 0000 0007 2ab4
0002 0460 ac00 0000 0200 0a00 0000 0600
0100 0000 0c00 0b00 0000 0c00 0100 0000
0700 0c00 0d00 0000 0100 1000 0000 0200
11
```

### 1.2.分析字节码

1. 使用Javap -verbose TestClass.class 编译出字节码内容

   ```java
     Last modified 2021-6-6; size 401 bytes
     MD5 checksum 93646e9c979da4d18206ab4dc0a68f2c
     Compiled from "TestClass.java"
   public class com.jvm.classFileFormat.TestClass
     minor version: 0
     major version: 52
     flags: ACC_PUBLIC, ACC_SUPER
   Constant pool:
      #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
      #2 = Fieldref           #3.#19         // com/jvm/classFileFormat/TestClass.m:I
      #3 = Class              #20            // com/jvm/classFileFormat/TestClass
      #4 = Class              #21            // java/lang/Object
      #5 = Utf8               m
      #6 = Utf8               I
      #7 = Utf8               <init>
      #8 = Utf8               ()V
      #9 = Utf8               Code
     #10 = Utf8               LineNumberTable
     #11 = Utf8               LocalVariableTable
     #12 = Utf8               this
     #13 = Utf8               Lcom/jvm/classFileFormat/TestClass;
     #14 = Utf8               inc
     #15 = Utf8               ()I
     #16 = Utf8               SourceFile
     #17 = Utf8               TestClass.java
     #18 = NameAndType        #7:#8          // "<init>":()V
     #19 = NameAndType        #5:#6          // m:I
     #20 = Utf8               com/jvm/classFileFormat/TestClass
     #21 = Utf8               java/lang/Object
   {
     public com.jvm.classFileFormat.TestClass();
       descriptor: ()V
       flags: ACC_PUBLIC
       Code:
         stack=1, locals=1, args_size=1
            0: aload_0
            1: invokespecial #1                  // Method java/lang/Object."<init>":()V
            4: return
         LineNumberTable:
           line 7: 0
         LocalVariableTable:
           Start  Length  Slot  Name   Signature
               0       5     0  this   Lcom/jvm/classFileFormat/TestClass;
   
     public int inc();
       descriptor: ()I
       flags: ACC_PUBLIC
       Code:
         stack=2, locals=1, args_size=1
            0: aload_0
            1: getfield      #2                  // Field m:I
            4: iconst_1
            5: iadd
            6: ireturn
         LineNumberTable:
           line 12: 0
         LocalVariableTable:
           Start  Length  Slot  Name   Signature
               0       7     0  this   Lcom/jvm/classFileFormat/TestClass;
   }
   SourceFile: "TestClass.java"
   ```

2. 使用idea插件打开

### 1.3.class文件的结构

#### 1.31.基本的数据类型:

1. 无符号数

   | 类型      | u1   | u2   | u4   | u8   |
   | --------- | ---- | ---- | ---- | ---- |
   | 大小/字节 | 1    | 2    | 4    | 8    |

2. "_info"结尾的表

#### 1.32.十六进制的字节码分别代表:

1. 魔数(Magic Number) 4字节
2. 版本号(Major Version) 4字节  ->52.0  次版本号(.0)+主版本号(52)
3. 常量池(Constant Pool) 
4. 访问标志(access_flags) 2字节
5. 等等...

## 2.JMM

### 2.1.一致性

- MESI协议

>Core0修改v后，发送一个信号，将Core1缓存的v标记为失效，并将修改值写回内存。
>
>Core0可能会多次修改v，每次修改都只发送一个信号（发信号时会锁住缓存间的总线），Core1缓存的v保持着失效标记。
>
>Core1使用v前，发现缓存中的v已经失效了，得知v已经被修改了，于是重新从其他缓存或内存中加载v。

- 总线锁

### 2.2.乱序

- 硬件

>1.内存屏障 X86
>
>sfence:  store| 在sfence指令前的写操作当必须在sfence指令后的写操作前完成。
>lfence：load | 在lfence指令前的读操作当必须在lfence指令后的读操作前完成。
>mfence：modify/mix | 在mfence指令前的读写操作当必须在mfence指令后的读写操作前完成。
>
>2.原子指令

- JVM级别如何规范（JSR133）

> LoadLoad屏障：
> 	对于这样的语句Load1; LoadLoad; Load2， 
>
> 	在Load2及后续读取操作要读取的数据被访问前，保证Load1要读取的数据被读取完毕。
>
> StoreStore屏障：
>
> 	对于这样的语句Store1; StoreStore; Store2，
> 	
> 	在Store2及后续写入操作执行前，保证Store1的写入操作对其它处理器可见。
>
> LoadStore屏障：
>
> 	对于这样的语句Load1; LoadStore; Store2，
> 	
> 	在Store2及后续写入操作被刷出前，保证Load1要读取的数据被读取完毕。
>
> StoreLoad屏障：
>
> ```
> 对于这样的语句Store1; StoreLoad; Load2，
> 
> 在Load2及后续所有读取操作执行前，保证Store1的写入对所有处理器可见。
> ```

### 2.3.volatile的实现细节

1. 字节码层面
   ACC_VOLATILE

2. JVM层面
   volatile内存区的读写 都加屏障

   > StoreStoreBarrier
   >
   > volatile 写操作
   >
   > StoreLoadBarrier

   > LoadLoadBarrier
   >
   > volatile 读操作
   >
   > LoadStoreBarrier

3. OS和硬件层面
   https://blog.csdn.net/qq_26222859/article/details/52235930
   hsdis - HotSpot Dis Assembler
   windows lock 指令实现 | MESI实现

### 2.4.synchronized实现细节

1. 字节码层面
   ACC_SYNCHRONIZED
   monitorenter monitorexit
2. JVM层面
   C C++ 调用了操作系统提供的同步机制
3. OS和硬件层面
   X86 : lock cmpxchg
   [https](https://blog.csdn.net/21aspnet/article/details/88571740)[://blog.csdn.net/21aspnet/article/details/](https://blog.csdn.net/21aspnet/article/details/88571740)[88571740](https://blog.csdn.net/21aspnet/article/details/88571740)

## 3.对象创建过程

1. class loading(类加载到内存)
2. class linking(类连接)
   1. verification(验证)
   2. preparation(静态变量给默认值)
   3. resolution(解析)
3. class initializing(静态变量初始值,静态语句块)
4. 申请对象内存
5. 成员变量赋默认值
6. 调用构造方法
   1. 成员变量,成员代码块
   2. 构造方法的方法体

## 4.对象在内存中的存储布局

**普通对象**(new Object() 16字节)

1. 对象头 markword 8字节

2. ==ClassPointer指针==(指向Class对象的指针)   -XX:+UseCompressedClassPointers 为4字节 不开启为8字节

3. 实例数据(8大基本==数据类型==大小,==引用数据类型== -XX:+UseCompressedOops 为4字节 不开启为8字节 ) 

4. padding 对齐(==保证对象的总大小为8的倍数==)

   上面的参数用java -XX:+PrintCommandLineFlags -version 观察虚拟机的配置

   ![image-20210606134921811](README.assets/image-20210606134921811.png)

**数组对象**(new int[]{} 16字节)

比普通对象多一个数组的长度length 4字节

## 5.对象头信息(markword)

![image-20210606164640042](README.assets/image-20210606164640042.png)

## 6.垃圾确认算法

### 6.1.Reference Counting(引用计数算法) 

>在对象中添加一个引用计数器,每当有一个地方引用它,计数器值就加一,当引用失效时,计数器的值就减一.任何时刻计数器为零的对象就是垃圾

```
缺点:循环引用对象无法被确认为垃圾
```

### 6.2.Root Seraching(可达性分析算法)

>通过一系列的"GC Roots"的根对象作为起始节点集,从这些节点开始,根据引用关系向下搜索,搜索过程所走的路径称为"引用链"(Reference Chain),如果某个对象到GC Roots间没有任何引用链相连,则这个对象是垃圾

GC Roots包括:

- JVM stack中引用的对象(方法堆栈的函数的参数,局部变量,临时变量)
- 方法区类静态属性引用的对象(Java类中引用类型静态变量)
- 方法区中常量引用的对象(字符串常量池)
- 本地方法Native引用的对象
- 虚拟机内部的引用(Class对象,异常对象,系统类加载器)
- synchronized持有的对象

## 7.垃圾回收算法

### 7.1.标记-清除算法

>标记所有需要回收的对象,标记完成后,统一回收所有被标记的对象,当然,也可以标记不需要回收的对象,标记完成后,统一回收没有被标记的对象

```
存活对象比较多的情况下效率高
缺点:需要两边扫描,效率偏低,容易产生碎片
```

### 7.2.标记-复制算法(年轻代,默认内存划分9:1)

>将内存分为大小相等的两块,每次只使用其中的一块,当这一块的内存用完了,就将还存活着的对象复制到另一块没有用的内存块上,然后回收使用过的这块内存的对象

```
使用存活对象较少的情况,只需扫描一次,效率提高,没有碎片
缺点:内存减半空间浪费,移动复制对象,需要调整对象的引用
```

### 7.3.标记-整理算法(老年代)

>让所有存活的对象都向内存空间的一端移动,然后回收边界以外另一端的内存

## 9.HotSpot(what is ?)

hotspot指的是热点代码探测技术

1. 通过==计数器找到最具编译价值的代码==,==触发及时编译或栈上替换==
2. 通过==编译器==与==解释器==协调工作,在程序==最优化的响应时间==与==最佳性能==中取得平衡

## 10.JVM整体详细模型

![image-20210609210427969](README.assets/image-20210609210427969.png)

## 11.类加载子系统

### 11.1.类加载大致过程

![image-20210609211559937](README.assets/image-20210609211559937.png)

### 11.2.类加载的几大步骤

![image-20210609212456023](README.assets/image-20210609212456023.png)

注意:初始化的阶段,一个类只能<clinit>()一次,多个线程的情况下会被同步加锁

[示例代码](src/main/java/com/jvm/clinit/TestSyncClinit.java)

### 11.3.类加载器的种类

![image-20210610161031760](README.assets/image-20210610161031760.png)

#### 11.3.1.引导类加载器

> Bootstrap Class Loader,使用C,C++编写,用于加载String等java核心类库,我们用户无法使用,只加载包名为java,javax,sun开头的类

#### 11.3.2.自定义类加载器

> 继承于java定义的ClassLoader虚基类的子类都为自定义类加载器
>
> 1. 扩展类加载器,ExtClassLoader,从java.ext.dirs系统属性所指定的目录中加载类库
> 2. 系统类加载器,默认是AppClassLoader,自己写的类的默认加载器

[代码链接](src/main/java/com/jvm/classLoader/TestClassLoader.java)

自定义类加载器步骤

![image-20210610160804269](README.assets/image-20210610160804269.png)

[代码链接](src/main/java/com/jvm/classLoader/CustomClassLoader.java)

## 12.双亲委派机制

![image-20210610163108846](README.assets/image-20210610163108846.png)

[验证代码](src/main/java/com/jvm/parentAppoint/StringTest.java)

[验证代码](src/main/java/java/lang/String.java)

![image-20210610164022595](README.assets/image-20210610164022595.png)

好处:

![image-20210610164110362](README.assets/image-20210610164110362.png)

[验证代码](src/main/java/java/lang/Test.java)

### 12.1.JVM中两个类是否为同一个类

>条件:
>
>1. 包名,类名一样
>2. 类加载器一样

## 13.运行时数据区(Runtime Data Areas )

![image-20210610190058265](README.assets/image-20210610190058265.png)

![image-20210610190128432](README.assets/image-20210610190128432.png)

### 13.1.PC寄存器

>作用:用来存储下一条指令的地址(==线程私有==)

```
pc寄存器存储字节码指令地址作用:
	答:cpu会不断的切换各个线程,切换回来就得知道接着从哪开始执行,pc寄存器里面放的地址就是
```

### 13.2.虚拟机栈

>what : 每个线程创建时都会创建一个虚拟机栈,其内部保存一个个的栈帧,对应者一次次的java方法的调用,一个栈帧对应一个Java方法(==线程私有==)
>
>作用: 保存==方法的局部变量,部分结果,参与方法的调用和返回==

[StackOverflowError](src/main/java/com/jvm/oom/TestStackOverflowError.java)

[StackOutOfMemory](src/main/java/com/jvm/oom/TestStackOutOfMemory.java)

![image-20210610201153860](README.assets/image-20210610201153860.png)

### 13.3.栈帧的内部结构

![image-20210610221601228](README.assets/image-20210610221601228.png)

#### 13.3.1.局部变量表

>数字数组,存储==方法参数==,和==定义在方法体内的局部变量==,以及==返回值类型==

![image-20210611102944454](README.assets/image-20210611102944454.png)

##### 13.3.1.1.变量槽(Slot)

>局部变量表就是用slot组成的数组,4个字节,long和double占2个slot,其他均只占一个

**slot重复利用**

![image-20210611105453758](README.assets/image-20210611105453758.png)

![image-20210611105922559](README.assets/image-20210611105922559.png)

[测试代码](src/main/java/com/jvm/SlotTest.java)

#### 13.3.2.操作数栈

![image-20210611112112152](README.assets/image-20210611112112152.png)

一个操作数栈的单位为4字节,8字节的数据占两个单位

#### 13.3.3.一个栈帧运行过程

1. 源代码和字节码

   ![image-20210611125757790](README.assets/image-20210611125757790.png)

2. 执行引擎执行

   ![image-20210611125858744](README.assets/image-20210611125858744.png)

![image-20210611125950036](README.assets/image-20210611125950036.png)

![image-20210611130056372](README.assets/image-20210611130056372.png)

![image-20210611130201739](README.assets/image-20210611130201739.png)

#### 13.3.4.动态链接

>指向运行时常量池的方法引用 
>
>![image-20210611134740427](README.assets/image-20210611134740427.png)

![image-20210611135103490](README.assets/image-20210611135103490.png)

#### 13.3.5.静态和动态链接

> 静态链接:目标方法在编译期间就可知,且运行期间保持不变,这种情况下,将调用方法的符号引用转换为直接引用就叫静态链接
>
> invokestatic, invokespecial, 

>动态链接:被调用的方法在编译期间无法确定,只有在运行期间将调用方法的符号引用转换为直接引用(多态)
>
>invokevirtual,invokeinterface

![image-20210611152821699](README.assets/image-20210611152821699.png)

[测试代码](src/main/java/com/jvm/Invoke/Son.java)

#### 13.3.6.invokedynamic指令

invokedynamic对于动态语言的支持,让java有了动态语言的特性,主要体现lambda

![image-20210611154955336](README.assets/image-20210611154955336.png)

[测试代码](src/main/java/com/jvm/Invoke/Lambda.java)

#### 13.3.7.方法重写的本质

> 1. 找到操作数栈顶的第一个元素所执行的对象的实际类型，记作C。
> 2. 如果在类型C中找到与常量中的描述符号简单名称都相符的方法，则进行访问权限校验，如果通过则返回这个方法的直接引用，查找过程结束；如果不通过，则返回java.lang.IllegalAccessError异常。
> 3. 否则，按照继承关系从下往上依次对C的各个父类进行第2步的搜索和验证过程。
> 4. 如果始终没有找到合适的方法，则抛出java.lang.AbstractMethodError异常。

IllegalAccessError：

程序试图访问或修改一个属性或调用一个方法，这个属性或方法，你没有权限访问。一般的这个会引擎编译器异常，这个错误如果发生在运行时，就说明一个类发生了不兼容的改变。

==为了提高效率,jvm在类加载的init阶段会预处理,创建一个虚方法表,存放各个方法的实际入口==

#### 13.3.8.方法返回地址

>正常退出,返回主调方法的PC计数器的值
>
>异常退出,不返回任何值(交给异常处理表确定)

对于有返回值的return指令不一样

[测试代码](src/main/java/com/jvm/returns/ReturnAddressTest.java)

主调方法在接到被调方法返回的PC计数器的值4,==但是5是什么时候压入到m2的操作数栈顶书上没说明白==

![image-20210611173404437](README.assets/image-20210611173404437.png)

#### 13.3.9.附加信息

>可能带有调试,性能收集信息

### 13.4本地方法栈

>专为本地方法native服务,其他和虚拟机栈一样,hotspot将本地方法栈和虚拟机栈合二为一,虚拟机栈直接可以调native方法

### 13.5.堆

![image-20210611213247010](README.assets/image-20210611213247010.png)

[默认占物理内存大小代码](src/main/java/com/jvm/heap/HeapSpaceInitial.java)

#### 13.5.1各类OOM

1. [堆空间OOM](src/main/java/com/jvm/oom/TestHeapOOM.java)
2. [栈空间OOM](src/main/java/com/jvm/oom/TestStackOutOfMemory.java)
3. [栈溢出](src/main/java/com/jvm/oom/TestStackOverflowError.java)
4. [字符串常量池OOM](src/main/java/com/jvm/oom/StringConstantPoolOOM.java)
5. [元空间OOM](src/main/java/com/jvm/oom/MetaspaceOOM.java)
6. [直接内存OOM](src/main/java/com/jvm/oom/DirectMemoryOOM.java)

#### 13.5.2.堆的分代比例设置

>在没有显式的设置`-XX:SurvivorRatio`=8的时候发现Eden:Survivor0=6,官方说开启了-XX:+UseAdaptiveSizePolicy自适应大小调整
>
>官方原话:
>
>如果禁用了自适应大小调整（使用该`-XX:-UseAdaptiveSizePolicy`选项），`-XX:SurvivorRatio`则应使用该选项为整个应用程序执行设置幸存者空间的大小。

[代码](src/main/java/com/jvm/memorySize/EdenSurvivorTest.java)

#### 13.5.3.对象在内存中的分配过程

![image-20210608160936478](README.assets/image-20210608160939637.png)

#### 13.5.3.几种GC

1. 部分收集

   1. 只回收年轻代 (Minor GC / YGC) : 当Eden区满触发,Survivor区满不会触发Minor GC
   2. 只回收老年代(Major GC / Old GC) : ==CMS收集器独有==
   3. 新生代+部分老年代(Mixed GC) : ==G1收集器独有==

2. 整堆收集(Full Gc)

   >触发条件: 
   >
   >1. 老年代满
   >2. 方法区满
   >3. 调用System.gc() 
   >4. Minor GC 导致survivor装不下,触发空间分配担保,但是失败了:==老年代连续空间小于新生代对象总大小==或==老年代连续空间小于====历次晋升的平均大====小==

#### 13.5.4.GC日志查看

[测试代码](src/main/java/com/jvm/gc/GcLog.java)

![GC日志详解](README.assets/GC%E6%97%A5%E5%BF%97%E8%AF%A6%E8%A7%A3.png)

![image-20210612205324033](README.assets/image-20210612205324033.png)

#### 13.5.5.动态年龄判定

>在HotSpot中,如果Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半,年龄大于或等于该年龄的对象直接进入老年代,没有年龄限制

#### 13.5.6.堆空间常用参数

```
 * -XX:+PrintFlagsInitial : 查看所有的参数的默认初始值
 * -XX:+PrintFlagsFinal  ：查看所有的参数的最终值（可能会存在修改，不再是初始值）
 *      具体查看某个参数的指令： jps：查看当前运行中的进程
 *                             jinfo -flag SurvivorRatio 进程id
 *
 * -Xms：初始堆空间内存 （默认为物理内存的1/64）
 * -Xmx：最大堆空间内存（默认为物理内存的1/4）
 * -Xmn：设置新生代的大小。(初始值及最大值)
 * -XX:NewRatio：配置新生代与老年代在堆结构的占比
 * -XX:SurvivorRatio：设置新生代中Eden和S0/S1空间的比例
 * -XX:MaxTenuringThreshold：设置新生代垃圾的最大年龄
 * -XX:+PrintGCDetails：输出详细的GC处理日志
 * 打印gc简要信息：① -XX:+PrintGC   ② -verbose:gc
 * -XX:HandlePromotionFailure：是否设置空间分配担保
```

### 13.6.逃逸分析

>分析对象动态作用域,当一个对象在方法里面被定义后,它可能被外部方法所引用,例如:
>
>1. 方法逃逸:对象作为调用参数传递到其他方法中
>2. 线程逃逸:对象赋值给可以在其他线程中访问的实例变量(如==给成员变量赋值==)

### 13.7.栈上分配

>允许方法逃逸,但是不允许线程逃逸

[测试代码](src/main/java/com/jvm/escapeAnalysis/StackAllocation.java)

### 13.8.同步消除

>被锁的对象没有线程逃逸,可以锁消除

[测试代码(有困惑)](src/main/java/com/jvm/escapeAnalysis/SyncElimination.java)

### 13.9.标量替换

>对于未发生==方法逃逸==的对象,在程序真正运行的时候可能==不去创建这个对象==,而改为直接==创建它的若干个被这个方法使用的成员变量来代替==

[测试代码](src/main/java/com/jvm/escapeAnalysis/ScalarReplace.java)

```
不会产生内存减半,不产生内存碎片
缺点:扫描两次,需要移动对象,效率偏低
```

### 13.10.方法区

JVM ==规范== : 方法区包括:(==Class信息,常量池,静态变量,即时编译的代码缓存==)

>**hotspot**
>
>JDK6 ==实现== : 永久代(Class信息+运行时常量池+字符串常量池+静态变量+即时编译的代码缓存) =====>jvm虚拟内存
>
>JDK7 实现 : 字符串常量池(堆中)  +  静态变量(堆中)    +  永久代==(运行时常量池+Class信息+即时编译的代码缓存)=======>还是jvm虚拟内存
>
>JDK8 实现: 字符串常量池(堆中)  +  静态变量(堆中)  +  元空间(运行时常量池+Class信息+即时编译的代码缓存)=====>本地内存

```
永久代为什么会被元空间代替?
答: 1.为永久代设置空间大小很难确定,在某些场景下,如果动态加载类过多,容易产生永久代的OOM.而元空间使用本地内存,默认只受本地内存限制
	2.对永久代垃圾回收很困难(回收常量相对简单,但回收一个类型Class的判断非常复杂-P74,会把FGC变得复杂)
```

```
为什么字符串常量池移到堆中?
答:  1.String.intern()方法的效率提升,节省了复制堆中的字符串到常量池中的成本  P61
	 2.堆内存比较大,不容易OOM
	 3.堆外的方法区的内存回收效率(可回收比例和执行时间成本)不如堆内
```


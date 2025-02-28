package com.nivelle.core.javacore.concurrent;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet
 *
 * @author nivelle
 * @date 2020/04/14
 */
public class CopyOnWriteArraySetMock {

    public static void main(String[] args) {
        /**
         *
         * 底层数据结构是:CopyOnWriteArrayList
         *
         * 1. Set 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突,它是线程安全的
         *
         * 2. 因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove() 等等）的开销很大。
         *
         * 3. 迭代器支持hasNext(), next()等不可变操作，但不支持可变 remove()等 操作
         *
         * 4. 使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照
         */
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        boolean setResult1 = copyOnWriteArraySet.add(1);
        System.out.println("copyOnWriteArraySet setResult1 is:" + setResult1);
        boolean setResult2 = copyOnWriteArraySet.add(2);
        System.out.println("copyOnWriteArraySet setResult2 is:" + setResult2);
        boolean setResult3 = copyOnWriteArraySet.add(3);
        System.out.println("copyOnWriteArraySet setResult3 is:" + setResult3);
        boolean setResult4 = copyOnWriteArraySet.add(3);
        System.out.println("copyOnWriteArraySet setResult4 is:" + setResult4);
        System.out.println("copyOnWriteArraySet is:" + copyOnWriteArraySet);
    }
}

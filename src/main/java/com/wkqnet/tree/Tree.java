package com.wkqnet.tree;

/**
 * @author 吴开桥
 * @version 1.0
 * @class Tree
 * @project wheel
 * @e-mail wkq003@gmail.com
 * @login wkq00
 * @date 2020/2/21 星期五
 * @ide IntelliJ IDEA
 * @description Tree interface
 */
public interface Tree<E> {
    int getSize();
    boolean put(E e);
    E remove(E e);
}

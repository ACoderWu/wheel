package com.wkqnet.tree;

import com.wkqnet.tree.node.TreeNode;

/**
 * @Class Tree
 * @Project wheel
 * @Author 吴开桥
 * @E-mail wkq003@gmail.com
 * @Login wkq00
 * @Date 2020/2/21 星期五
 * @Version 1.0
 * @IDE IntelliJ IDEA
 * @Description BaseTree
 */
public class BaseTree<E extends Comparable<? super E>> implements Tree<E>{
    TreeNode<E> root = new TreeNode<E>();
    int size;

    public int getSize() {
        return size;
    }

    public boolean put(E e) {
        return false;

    }

    /**
     *
     * @param e
     * @return 返回被删除元素
     */
    public E remove(E e) {
        return null;
    }

    public TreeNode<E> getLeft(TreeNode<E> node) {
        return null;
    }

    public TreeNode<E> getRight(TreeNode<E> node) {
        return null;
    }

    public boolean addToLeft(TreeNode<E> node) {
        return true;
    }

    public boolean addToRight(TreeNode<E> node) {
        return false;
    }

    public E replace(TreeNode<E> node) {
        return null;
    }
}

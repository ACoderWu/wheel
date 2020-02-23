package com.wkqnet.tree.node;

/**
 * @Class TreeNode
 * @Project wheel
 * @Author 吴开桥
 * @E-mail wkq003@gmail.com
 * @Login wkq00
 * @Date 2020/2/21 星期五
 * @Version 1.0
 * @IDE IntelliJ IDEA
 * @Description Tree Node
 */
public class TreeNode<V> {
    V value;
    TreeNode<V> left;
    TreeNode<V> right;

    public TreeNode() {
    }

    public TreeNode(V value) {
        this.value = value;
    }

    public TreeNode(V value, TreeNode<V> left, TreeNode<V> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public TreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<V> left) {
        this.left = left;
    }

    public TreeNode<V> getRight() {
        return right;
    }

    public void setRight(TreeNode<V> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        V valueLeft = null;
        if(left != null) {
            valueLeft = left.value;
        }

        V valueRight = null;
        if(right != null) {
            valueRight = right.value;
        }

        return "TreeNode{" +
                "value=" + value +
                ", left=" + valueLeft +
                ", right=" + valueRight +
                '}';
    }
}

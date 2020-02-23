package com.wkqnet.sort.bubble;

import com.wkqnet.sort.publicmethod.Swap;

/**
 * @author 吴开桥
 * @version 1.0
 * @class BubbleSort
 * @project wheel
 * @e-mail wkq003@gmail.com
 * @login wkq00
 * @date 2020/2/23 星期日
 * @ide IntelliJ IDEA
 * @description 冒泡排序
 */
public class BubbleSort<T extends Comparable<? super T>> {
    public void sort(T[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (arr.length <= 1) {
            return;
        }
        boolean hadSort = false;

        do {
            for (int index = 0; index < arr.length; index++) {
                if (arr[index].compareTo(arr[index + 1]) > 0) {
                    Swap.Companion.swap(arr, index, index + 1);
                    hadSort = true;
                }
            }
        } while (hadSort);
    }
}

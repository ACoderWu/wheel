package com.wkqnet.sort.test;

import com.wkqnet.sort.bubble.BubbleSort;
import com.wkqnet.sort.insert.InsertionSort;
import com.wkqnet.sort.merge.MergeSort;
import com.wkqnet.sort.quick.QuickSort;
import com.wkqnet.sort.quick.QuickSort3Way;
import com.wkqnet.sort.select.SelectionSort;

/**
 * @author 吴开桥
 * @version 1.0
 * @class SortTestMain
 * @project wheel
 * @e-mail wkq003@gmail.com
 * @login wkq00
 * @date 2020/2/23 星期日
 * @ide IntelliJ IDEA
 * @description 排序测试入口
 */
public class SortTestMain {
    public static void main(String[] args) {
        SortTest test = new SortTest();
        test.addSortMethods(BubbleSort.class, InsertionSort.class, SelectionSort.class);
        test.addSortMethod(MergeSort.class);
        test.addSortMethods(QuickSort.class, QuickSort3Way.class);
        test.sortTest(10, QuickSort.class);
        test.sortTest(100, QuickSort.class);
        test.sortTest(1000, QuickSort.class);
        test.sortTest(10);
        test.sortTest(100);
        test.sortTest(1000);
        test.sortTest(10000);
        //十万级时快排就会出现栈溢出了
        test.removeSortMethod(QuickSort.class);
        test.sortTest(100000);
        test.printResults();
    }
}

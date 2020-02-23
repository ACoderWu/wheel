package com.wkqnet.sort.publicmethod

/**
 * @class Swap
 * @project wheel
 * @author 吴开桥
 * @e-mail wkq003@gmail.com
 * @login wkq00
 * @date 2020/2/23 星期日
 * @version 1.0
 * @ide IntelliJ IDEA
 * @description 交换数组两数
 */
class Swap {
    companion object {
        fun <T> swap(arr: Array<T>, index1: Int, index2: Int) {
            val temp: T = arr[index1]
            arr[index1] = arr[index2]
            arr[index2] = temp;
        }
    }
}
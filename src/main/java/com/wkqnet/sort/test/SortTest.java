package com.wkqnet.sort.test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author 吴开桥
 * @version 1.0
 * @class SortTest
 * @project wheel
 * @e-mail wkq003@gmail.com
 * @login wkq00
 * @date 2020/2/23 星期日
 * @ide IntelliJ IDEA
 * @Description 测试各种排序法的性能
 */
public class SortTest {
    private int[] arr;
    /**
     * 排序方法数组
     */
    private List<Method> sortMethods = new ArrayList<>();

    /**
     * 保存最后一次测试结果
     */
    private List<Result> result = new ArrayList<>();

    /**
     * 保存所有次数的测试结果
     */
    private List<Results> results = new ArrayList<>();

    /**
     * 对一个方法的一轮测试结果
     */
    private static final class Result {
        String sortName;
        long time;

        Result(String sortName, long time) {
            this.sortName = sortName;
            this.time = time;
        }

        @Override
        public String toString() {
            String table = sortName.length() > 12 ? "\t" : "\t\t";
            String time = String.format("%1$12s", (this.time));
            return "|" + sortName + table + "|" + time + "纳秒 |";
        }
    }

    /**
     * 对一个数量级的一次测试结果
     */
    private static final class Results {
        int magnitude;
        List<Result> result;

        public Results(int magnitude, List<Result> result) {
            this.magnitude = magnitude;
            this.result = result;
        }
    }

    /**
     * 获取所有测试结果
     *
     * @return 返回所有测试结果
     */
    public List<Results> getResults() {
        return this.results;
    }

    /**
     * 打印输出所有测试的最终结果
     */
    public void printResults() {
        for (Results results : getResults()) {
            printOut(results.result, results.magnitude);
        }
    }

    /**
     * 对某次测试结果打印输出到文件
     */
    private void printOut(List<Result> results, int magnitude) {
        String magnitudeStr = String.format("%1$9s", (magnitude));
        String time = String.format("%1$9s", "");
        StringBuilder sb = new StringBuilder();
        sb.append(" ---------------------------------\n");
        sb.append("| 数量级：\t\t|\t").append(magnitudeStr).append("量级 |\n");
        sb.append(" ---------------------------------\n");
        sb.append("| 排序方法：\t\t|\t").append(time).append("时间 |\n");
        sb.append(" ---------------------------------\n");
        for (Result result : results) {
            sb.append(result).append("\n");
        }
        sb.append(" ---------------------------------\n");

        //创建文件和文件输出流对象
        File dir = new File(System.getProperty("user.dir"));
        File file = new File(dir, "result.txt");
        FileOutputStream fos = null;
        if (!file.exists()) {
            try {
                //如果文件不存在，就创建该文件
                if (file.createNewFile()) {
                    //创建成功首次写入获取
                    fos = new FileOutputStream(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                //如果文件存在则打开文件末尾追加
                fos = new FileOutputStream(file, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //写出流将常量池中的字符串写出到文件
        BufferedWriter bw = null;
        try {
            if (fos != null) {
                Writer writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                bw = new BufferedWriter(writer);
                bw.write(String.valueOf(sb));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    //写出到文件并关闭流
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成一个指定长度的随机数组
     *
     * @param size 指定长度
     *
     * @return 返回随机数组引用
     */
    private int[] generateArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    /**
     * 加入待测试排序方法
     *
     * @param classes 待测试排序方法的字节码对象数组
     */
    public void addSortMethods(Class<?>... classes) {
        for (Class<?> c : classes) {
            try {
                //利用反射获取排序方法
                sortMethods.add(c.getDeclaredMethod("sort", int[].class));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加入待测试排序方法
     *
     * @param c 待测试排序方法的字节码对象
     */
    public void addSortMethod(Class<?> c) {
        try {
            //利用反射获取排序方法
            sortMethods.add(c.getDeclaredMethod("sort", int[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除某个方法
     * @param c 该方法的字节码对象
     */
    public void removeSortMethod(Class<?> c) {
        try {
            //利用反射获取排序方法
            sortMethods.remove(c.getDeclaredMethod("sort", int[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按测数量级引入测试方法
     *
     * @param size 测试数组的数量级
     */
    public void sortTest(int size) {
        this.arr = generateArray(size);
        sort();
        //printOut(result, arr.length);
    }

    /**
     * 对单独某个排序方法进行测试
     *
     * @param size 测试数组的数量级
     * @param c    单独某个排序方法的字节码对象
     */
    public void sortTest(int size, Class<?> c) {
        Method sortMethod = null;
        this.arr = generateArray(size);
        try {
            sortMethod = c.getMethod("sort", int[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        List<Method> methods = sortMethods;
        sortMethods = new ArrayList<>();
        sortMethods.add(sortMethod);
        sort();
        //printOut(result, arr.length);
        sortMethods = methods;
    }

    /**
     * 排序
     */
    private void sort() {
        result.clear();
        ArrayList<Result> result = new ArrayList<>();
        int[] arr = this.arr;
        for (Method sort : sortMethods) {
            //截取短方法名
            String sortName = sort.getDeclaringClass().toString().substring(11);
            try {
                //排序并记录花费时间
                long start = System.nanoTime();
                sort.invoke(null, arr);
                long end = System.nanoTime();
                long time = end - start;
                result.add(new Result(sortName, time));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //对结果由时间从短到长进行排序
        Comparator<Result> cmp = Comparator.comparingLong(o -> o.time);
        result.sort(cmp);

        this.results.add(new Results(arr.length, (List<Result>) result.clone()));
        this.result = result;
    }
}
package cjw.pers.datastu;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author ChenJingwei
 * @date 2023/4/13 16:09
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 7;
        int myI = myBinarySearch(a, target);
        int i = binarySearch(a, target);
        System.out.println(i);

        int[] b = {1, 2, 3, 3, 3, 4, 5, 6, 7};
        int tar = 3;

    }

    @Test
    @DisplayName("找到了")
    public static void test01() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 7;
        int i = 0;
        int j = Integer.MAX_VALUE;
        int m = (i + j) / 2;
        i = m + 1;
        System.out.println((i + j) / 2); // 这样会超过正整数能够表示的最大范围 所以使用无符号右移代替这一步操作
    }

    // 我的答案
    public static int myBinarySearch(int[] a, int target) {
        int i = 0;
        int j = a.length;
        while (true) {
            if (i > j) {
                return -1;
            } else {
                int m = (int) Math.floor((i + j) / 2);
                if (target < a[m]) {
                    j = m - 1;
                } else if (target > a[m]) {
                    i = m + 1;
                } else {
                    return m;
                }
            }
        }
    }

    // 将target按顺序插进去
    public static int myBinaryInsert(int[] a, int target) {
        int i = 0;
        int j = a.length - 1;
        while (j >= i) {
            int mid = (i + j) >>> 1;
            if (a[mid] > target) {
                j = mid - 1;
            } else if (a[mid] < target) {
                i = mid + 1;
            } else {
                return mid;
            }
        }
        return -(i + 1);
    }

    /*
    二分查找基础版
    param: a:升序待查找数组
           target:待查找目标值
    Return: 返回目标值索引，没找到返回-1
    */
    public static int binarySearch(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {                    // 先设置两个指针
            int m = (i + j) >>> 1;          // 无符号右移
            if (target < a[m]) {            // 在左边
                j = m - 1;
            } else if (a[m] < target) {        // 在右边
                i = m + 1;                  // 都写成小于符号 更符合升序数组的目视理解
            } else {
                return m;
            }
        }
        return -1;
    }

    /*
    二分查找改动版
    param: a:升序待查找数组
           target:待查找目标值
    Return: 返回目标值索引，没找到返回-1
    */
    public static int newBinarySearch(int[] a, int target) {
        int i = 0, j = a.length;
        while (i < j) {                     // 先设置两个指针
            int m = (i + j) >>> 1;          // 无符号右移
            if (target < a[m]) {            // 在左边
                j = m;
            } else if (a[m] < target) {        // 在右边
                i = m + 1;                  // 都写成小于符号 更符合升序数组的目视理解
            } else {
                return m;
            }
        }
        return -1;
    }

    /*
    二分查找平衡版
    上述的二分查找当待查找目标在左边和右边时显然判断次数不同
    param: a:升序待查找数组
           target:待查找目标值
    Return: 返回目标值索引，没找到返回-1
    */
    public static int binarySearchBalance(int[] a, int target) {
        int i = 0, j = a.length;
        while (1 < j - i) {          // 范围内的待查找元素个数
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        return (a[i] == target) ? i : -1;
    }

    /*
    二分查找java内置版
    使用的是基础版二分查找
    上述的二分查找当待查找目标在左边和右边时显然判断次数不同
    param: a:升序待查找数组
           target:待查找目标值
    Return: 返回目标值索引，没找到返回-1
    */
    private static int binarySearch0(long[] a, int fromIndex, int toIndex,
                                     long key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.这返回的是-插入点-1
    }

    @Test
    public static void test02() {
        long[] a = {1, 2, 4, 5, 6};
        long target = 3;
        System.out.println(binarySearch0(a, 0, a.length, target));
    }

    /*
    应用:leftmost 和 rightmost
    param: a:升序待查找数组
           target:待查找目标值
    Return: 返回目标值索引，没找到返回-1
    */
    public static int binarySearchLeftmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m; // 记录候选位置
                j = m - 1;     // 继续向左
            }
        }
        return candidate;
    }

    public static int binarySearchRightmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m; // 记录候选位置
                i = m + 1;     // 继续向右边
            }
        }
        return candidate;
    }

}

package cjw.pers.datastu;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ChenJingwei
 * @date 2023/4/13 15:05
 */
public class Test01 {
    public static void main(String[] args) {

    }
    @Test
    public static void test01() {
        int[] list01 = new int[10];
        for (int i = 0; i < list01.length; i++) {
            list01[i] = i;
        }
        for (int i :
                list01) {
            System.out.print(i + "  ");
        }
        System.out.println("hello" + " java");

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(9,100);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.print(next + " ");
        }

    }

}

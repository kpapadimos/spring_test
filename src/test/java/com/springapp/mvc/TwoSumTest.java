package com.springapp.mvc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by papadimos on 1/12/2014.
 */
public class TwoSumTest {

    @Test
    public void twoSum() {
        int[] indices = findTwoSum(new int[] { 1, 3, 5, 7, 9 }, 12);
        System.out.println(indices[0] + " " + indices[1]);
    }

    public static int[] findTwoSum(int[] list, int sum) {
        List<Integer> where = new ArrayList<Integer>();
        int res = 0;
        for (int i : list) {
            System.out.println(i);
        }
        int[] simpleArray = new int[ where.size() ];
        for (int i=0; i < simpleArray.length; i++)
        {
            simpleArray[i] = where.get(i);
        }
        return simpleArray;
    }


}

package com.springapp.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by papadimos on 14/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ZeroBasedTest {

    @Test
    public void zeroBased() throws Exception {
        String s = "dddddabbcccddddcccbba";
        char previous = '\0';
        NavigableMap<String, Integer> myMap = new TreeMap<String, Integer>();

        for(int i = 0, n = s.length() ; i < n ; i++) {
            char c = s.charAt(i);

            if (previous == c) { // If previous character matches current
                if (myMap.containsKey(c + "")) {
                    myMap.put(c + "", Integer.parseInt(myMap.get(c + "").longValue()+1 + ""));
                } else {
                    myMap.put(c + "", Integer.parseInt("1"));
                }
            } else {
                myMap.put(c + "", Integer.parseInt("1"));
            }
            previous = c;
        }

        /*String searchString = "";
        for(int j = 0; j < myMap.lastEntry().getValue() ; j++) {
            searchString+=myMap.lastEntry().getKey();
        }

        System.out.println(s.indexOf(searchString));*/
        System.out.println("END");
    }
}

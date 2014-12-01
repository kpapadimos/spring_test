package com.springapp.mvc;

import com.springapp.mvc.core.api.client.ApiWebClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by papadimos on 1/12/2014.
 */
public class AreAnagramsTest {

    @Test
    public void areAnagrams() throws Exception {
        System.out.println(areAnagrams("dadmomdad", "dadmomdad"));
    }

    public static boolean areAnagrams(String a, String b) {
        char[] word1 = a.replaceAll("[\\s]", "").toCharArray();
        char[] word2 = b.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }

    public static boolean areAnagrams2(String a, String b) {
        boolean res = true;
        if (a.length() != b.length()) {
            return false;
        } else {
            System.out.println(b.length());
            int j = b.length();
            for (int i = 0; i < b.length(); i++) {
                res = b.charAt(i) == a.charAt(j-1);
                System.out.println(b.charAt(i) + "---" + a.charAt(j-1));
                j--;
                if (!res) {
                    break;
                }
            }
        }
        return res;
    }
}

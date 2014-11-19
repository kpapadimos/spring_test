package com.springapp.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by papadimos on 14/11/2014.
 */
public class PalindromeTest {

    @Test
    public void palindrome(){
        //String strInit = "Noel sees Leon.";
        String strInit = "Bc       cb%$#$!@#!$%*^&(()[][}{[]|||";
        String strOnlyLetters = strInit.replaceAll("[^A-Za-z ]", "");
        String strFinal = strOnlyLetters.replaceAll("\\s+", "").toLowerCase();
        System.out.println(strInit + "--" + strFinal);
        System.out.println(strFinal.length());
        String strPart1 = strFinal.substring(0, strFinal.length()/2);
        System.out.println(strPart1);
        String strPart2 = strFinal.substring(strFinal.length()/2, strFinal.length());
        System.out.println(strPart2);

        String reverse = "";
        //Scanner in = new Scanner(System.in);

        int length = strPart2.length();

        for ( int i = length - 1 ; i >= 0 ; i-- )
            reverse = reverse + strPart2.charAt(i);
        System.out.println(reverse);
        System.out.println(strPart1.equals(reverse));
    }
}

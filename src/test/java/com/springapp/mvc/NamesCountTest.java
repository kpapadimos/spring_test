package com.springapp.mvc;

import com.springapp.mvc.domain.NamesCount;
import org.junit.Test;

/**
 * Created by papadimos on 14/11/2014.
 */
public class NamesCountTest {
    @Test
    public void namesCount() {

        NamesCount namesCount = new NamesCount();

        namesCount.addName("John");
        namesCount.addName("Mary");
        namesCount.addName("Mary");

        System.out.println("Fraction of Johns: " + namesCount.nameProportion("John"));
        System.out.println("Fraction of Marys: " + namesCount.nameProportion("Mary"));
    }
}

package com.springapp.mvc.domain;

import java.util.HashMap;

/**
 * Created by papadimos on 27/10/2014.
 */
public class NamesCount {

    private static int count;
    private HashMap<String, Integer> counts = new HashMap<String, Integer>();

    /**
     * Adds the name.
     * @param name Name.
     */
    public void addName(String name) {
        //Integer nameCount = counts.get(name);

        if (counts.containsKey(name)) {
            counts.put(name, counts.get(name).intValue() + 1);
        } else {
            counts.put(name, 1);
        }

//        if (nameCount == null) {
//            nameCount = 0;
//            counts.put(name, nameCount);
//        }

        count++;
    }

    /**
     * Returns proportion of parameter name in all calls to addName.
     * @param name Name.
     * @return Double in interval [0, 1]. Returns 0 if AddName has not been called.
     */
    public double nameProportion(String name) {
        if (counts.get(name) != null) {
            return Double.parseDouble(counts.get(name).intValue() + "") / Double.parseDouble(count + "");
        } else {
            return 0;
        }
    }
}

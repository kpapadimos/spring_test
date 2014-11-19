package com.springapp.mvc;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by papadimos on 23/10/2014.
 */
public class Utilities {
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof IsEmpty) {
            return ((IsEmpty) value).isEmpty();
        } else if (value instanceof String) {
            return (((String) value).trim().length() == 0);
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        } else return value.getClass().isArray() && (Array.getLength(value) == 0);
    }
}

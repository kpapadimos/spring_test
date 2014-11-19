package com.springapp.mvc;

import com.springapp.mvc.domain.Animal;
import com.springapp.mvc.domain.Dog;
import com.springapp.mvc.domain.Mammal;
import org.junit.Test;

/**
 * Created by papadimos on 14/11/2014.
 */
public class InheritanceTest {
    @Test
    public void inheritance() throws Exception {
        Animal a = new Animal();
        Mammal m = new Mammal();
        Dog d = new Dog();

        System.out.println(m instanceof Animal);
        System.out.println(d instanceof Mammal);
        System.out.println(d instanceof Animal);
        System.out.println(a instanceof Mammal);
    }
}

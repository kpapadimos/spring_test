package com.springapp.mvc;

import java.util.Scanner;

/**
 * Created by papadimos on 14/11/2014.
 */
class JavaTests {

    public static void mainScannerSimple(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You just typed: " + scanner.nextLine());
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        double fnum, snum, answer;
        System.out.println("Enter first num: ");
        fnum = scanner.nextDouble();
        System.out.println("Enter second num: ");
        snum = scanner.nextDouble();
        answer = fnum + snum;
        System.out.println("Result is: " + answer);
    }
}

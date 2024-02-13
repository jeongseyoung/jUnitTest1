package com.example.junittest1.mine;

import java.util.*;

public class t1 {
    public static void main(String[] args) {
        Vector<Integer> v1 = new Vector<>(1);
        System.out.println("v1 size: " + v1.capacity());

        String etc = "*";
        StringBuilder str = new StringBuilder(etc);
        for (int i = 0; i <= 9; i++) {
            str.append("*");
        }
        System.out.println(str);

        long num = 1L;
        for (int i = 0; i <= 10000; i++) {
            num += 3;
        }
        System.out.println("Long num: " + num);
    }
}

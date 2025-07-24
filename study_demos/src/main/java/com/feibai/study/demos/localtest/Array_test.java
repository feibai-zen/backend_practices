package com.feibai.study.demos.localtest;

import java.util.*;

public class Array_test {

    public static void main(String[] args) {
        int[] arr = new int[10];

        System.out.println(arr.length);


        Queue<String> queue = new LinkedList<String>();

        int[] element = new int[10];
        for (int i = 0; i < 10; i++) {
            element[i] = i;
        }
        int[] newArray = Arrays.copyOf(element, element.length + 1);

        System.out.println(Arrays.toString(newArray));
    }
}

package com.feibai.study.demos.localtest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue);

        System.out.println(queue.size());
        System.out.println(Arrays.toString(queue.toArray()));

        System.out.println(queue.remove());
        Integer[] a = new Integer[10];

        System.out.println(Arrays.toString(queue.toArray(a)));

        System.out.println(Arrays.toString(a));
    }

}

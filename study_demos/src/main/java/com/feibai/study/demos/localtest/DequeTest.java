package com.feibai.study.demos.localtest;

import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();

        deque.offerFirst(1);
        System.out.println(deque.size());
        deque.offer(2);
        deque.offer(3);
        System.out.println(deque.poll());
        System.out.println(deque);
    }

}

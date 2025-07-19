package com.feibai.study.demos.localtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();

        map.put("a", new ArrayList<>());

        map.put("b", new ArrayList<>());

        List<String> list = map.computeIfAbsent("c", key -> new ArrayList<>());
        list.add("hahah,success");
        System.out.println(map);

        List<String> ret = map.computeIfPresent("c", (key, value) -> {
            value.add("mmm");
            return value;
        });
        System.out.println(ret);
    }
}

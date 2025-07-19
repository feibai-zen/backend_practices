package com.feibai.study.demos.multithread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化对象为HashMap
 *
 * @author feibai
 */
public class ThreadLocal_demo06 {
    // 让ThreadLocal里面有一个默认的类即在初始化时 new 出来
    protected final static ThreadLocal<Map<Object, Object>> threadContext = new MapThreadLocal();

    private static class MapThreadLocal extends ThreadLocal<Map<Object, Object>> {
        @Override
        protected Map<Object, Object> initialValue() {
            return new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Map<Object, Object> objectObjectMap = threadContext.get();
        System.out.println(objectObjectMap == null);
    }
}

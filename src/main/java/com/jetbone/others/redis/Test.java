package com.jetbone.others.redis;

import redis.clients.jedis.Jedis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Chris
 * @date 2021-03-13
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Jedis jedis = new Jedis("localhost");
        System.out.println("链接成功");
        jedis.set("key", "value");
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "Test");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
        objectOutput.writeObject(map);
        byte[] bytes = outputStream.toByteArray();

        jedis.set("Object".getBytes(StandardCharsets.UTF_8), bytes);

        byte[] byteMap = jedis.get("Object".getBytes(StandardCharsets.UTF_8));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteMap);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Map<Long, String> mapResult = (Map<Long, String>) objectInputStream.readObject();
        System.out.println("map.get()");
    }
}

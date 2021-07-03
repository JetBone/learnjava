package com.jetbone.others.april.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2019/3/21.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println(((TestInterface)(a, b) -> a+b).func(1, 2));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonTest jsonTest = new JsonTest();
        jsonTest.setCode("1");
        jsonTest.setName("2");
        List<JsonTest> list = new ArrayList<>();
        list.add(jsonTest);
        var result = objectMapper.writeValueAsString(list);
        System.out.println(result);


    }

    @Data
    static class JsonTest {
        private String code;

        private String name;

        private String value;

        @JsonIgnore
        private int index;
    }

}

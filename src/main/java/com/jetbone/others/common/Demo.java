package com.jetbone.others.common;


import com.jetbone.others.inherit.Father;
import com.jetbone.others.inherit.Son;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.lang.model.element.NestingKind;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2020/7/5
 */
@Slf4j
public class Demo {

    private static final Pattern PATTERN = Pattern.compile("(?<=\\[#)[a-zA-Z0-9]+(?=#])");
    private static final Pattern REJECTION_PATTERN = Pattern.compile("(?<=in:\\[).+(?=])");

    public static void main(String[] args) throws Exception {

        TestClass t1 = new TestClass(1L, BigDecimal.valueOf(10));
        TestClass t2 = new TestClass(2L, BigDecimal.valueOf(12));
        TestClass t3 = new TestClass(3L, BigDecimal.valueOf(13));
        TestClass t4 = new TestClass(4L, BigDecimal.valueOf(14));
        TestClass t4_1 = new TestClass(4L, BigDecimal.valueOf(15));
        TestClass t5 = new TestClass(5L, BigDecimal.valueOf(16));
        TestClass t5_1 = new TestClass(5L, BigDecimal.valueOf(17));
        TestClass t6 = new TestClass(6L, BigDecimal.valueOf(18));
        TestClass t7 = new TestClass(null, BigDecimal.valueOf(19));
        TestClass t8 = new TestClass(null, BigDecimal.valueOf(20));

        List<TestClass> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        list1.add(t3);
        list1.add(t4);
        list1.add(t5);

        List<TestClass> list2 = new ArrayList<>();
        list2.add(t4_1);
        list2.add(t5_1);
        list2.add(t6);
        list2.add(t7);
        list2.add(t8);
//
//        Map<Long, BigDecimal> sumMap = testClasses.stream().collect(Collectors.groupingBy(TestClass::getId, Collectors.reducing(BigDecimal.ZERO, TestClass::getValue, BigDecimal::add)));
//        System.out.println(sumMap);

//        BigDecimal id = BigDecimal.valueOf(31431.432D);
//        String result = MessageFormat.format("{0}", id);
//        System.out.println(result);

//        System.out.println(Math.random()*9+1);

        List<Long> ids = list1.stream().map(TestClass::getId).collect(Collectors.toList());
        List<TestClass> updateList = list2.stream()
                .filter(l2 -> ids.contains(l2.getId()))
                .collect(Collectors.toList());
        List<Long> ids2 = updateList.stream().map(TestClass::getId).collect(Collectors.toList());
        List<TestClass> insertList = list2.stream().filter(l2 -> !ids2.contains(l2.getId())).collect(Collectors.toList());
        List<TestClass> deleteList = list1.stream().filter(l1 -> !ids2.contains(l1.getId())).collect(Collectors.toList());

        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(updateList.toString());
        System.out.println(insertList.toString());
        System.out.println(deleteList.toString());

    }
}

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

//        TestClass t1 = new TestClass(1L, BigDecimal.valueOf(10));
//        TestClass t2 = new TestClass(1L, BigDecimal.valueOf(12));
//        TestClass t3 = new TestClass(2L, BigDecimal.valueOf(14));
//        TestClass t4 = new TestClass(2L, BigDecimal.valueOf(15));
//        TestClass t5 = new TestClass(3L, BigDecimal.valueOf(17));
//        TestClass t6 = new TestClass(4L, BigDecimal.valueOf(19));
//
//        List<TestClass> testClasses = new ArrayList<>();
//        testClasses.add(t1);
//        testClasses.add(t2);
//        testClasses.add(t3);
//        testClasses.add(t4);
//        testClasses.add(t5);
//        testClasses.add(t6);
//
//        Map<Long, BigDecimal> sumMap = testClasses.stream().collect(Collectors.groupingBy(TestClass::getId, Collectors.reducing(BigDecimal.ZERO, TestClass::getValue, BigDecimal::add)));
//        System.out.println(sumMap);

//        BigDecimal id = BigDecimal.valueOf(31431.432D);
//        String result = MessageFormat.format("{0}", id);
//        System.out.println(result);

//        System.out.println(Math.random()*9+1);
        int rand = (int)(Math.random()*9+1)*10000;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();

        String code = df.format(now) + rand;
        System.out.println(code);
        System.out.println(Long.valueOf(code));

    }
}

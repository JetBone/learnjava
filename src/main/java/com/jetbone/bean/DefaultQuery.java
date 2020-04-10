package com.jetbone.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by Chris on 2020/4/10
 */
@Data
public class DefaultQuery {

    // 使用 pattern，系统认为接收到的字符串就已经是当前系统时区了，转正 date 之后年月日不变，时间全部为0，时区自动设置为当前系统时区（+8）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 使用 iso 配置，注释上看和上述格式一样，但是结果上，系统会认为接收到的字符串是 UTC 时间，转成 date 之后年月日不变，但是时间会自动帮你+8小时，时区设为系统时区（+8）
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date date;

}

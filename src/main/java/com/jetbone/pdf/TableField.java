package com.jetbone.pdf;

import java.lang.annotation.*;

/**
 * Created by Chris on 2020/3/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface TableField {

    String title();

}

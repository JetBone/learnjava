package com.jetbone.others.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Chris
 * @date 2021-01-18
 */
@Data
@RequiredArgsConstructor
public class TestClass {

    private final Long id;

    private final BigDecimal value;
}

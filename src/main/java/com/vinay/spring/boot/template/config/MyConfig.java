package com.vinay.spring.boot.template.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    public double sum(List<? extends Number> numbers) {
        double sum = 0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
}

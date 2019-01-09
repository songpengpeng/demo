package com.laohans.java8.lambda;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
class Man {
    private String name;

    public static Man covert(ArraysPerson arraysPerson) {
        return Man.builder().name(arraysPerson.getName()).build();
    }
}

package com.laohans.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class ArraysPersonTest {
    @Test
    public void test_stream_to_list() {
        ArraysPerson[] persons = {
                ArraysPerson.builder().name("p1").build(),
                ArraysPerson.builder().name("p2").build(),
                ArraysPerson.builder().name("p3").build()
        };
        List<ArraysPerson> list = Arrays.stream(persons, 1, persons.length).collect(Collectors.toList());
        assertThat(list.size(), is(2));
    }

    @Test
    public void test_stream_map() {
        ArraysPerson[] persons = {
                ArraysPerson.builder().name("p1").build(),
                ArraysPerson.builder().name("p2").build(),
                ArraysPerson.builder().name("p3").build()
        };
        List<Man> list = Arrays.stream(persons, 1, persons.length).map(Man::covert).collect(Collectors.toList());
        assertThat(list.size(), is(2));
    }
}
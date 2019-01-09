package com.laohans.java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GroupPersonTest {

    @Test
    public void test_group() {
        List<GroupPerson> persons = Lists.newArrayList(
                GroupPerson.builder().sex(1).name("man-1").build(),
                GroupPerson.builder().sex(1).name("man-2").build(),
                GroupPerson.builder().sex(2).name("woman-1").build(),
                GroupPerson.builder().sex(2).name("woman-2").build()
        );
        Map<Integer, ArrayList<GroupPerson>> map = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                GroupPerson::getSex,
                                Collectors.toCollection(ArrayList::new)
                        )
                );
        assertThat(map.get(1).size(), is(2));
        assertThat(map.get(2).size(), is(2));
    }
}
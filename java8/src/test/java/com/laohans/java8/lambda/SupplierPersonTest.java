package com.laohans.java8.lambda;

import com.google.common.base.Suppliers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SupplierPersonTest {
    private final ByteArrayOutputStream content = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(content));
    }

    @Test
    public void should_print_1_time() {
        SupplierPerson person = SupplierPerson.builder().build();
        Supplier<SupplierPerson> supplier = Suppliers.memoizeWithExpiration(person, 1, TimeUnit.SECONDS);
        supplier.get();
        supplier.get();
        assertThat("get ", is(content.toString()));
    }

    @Test
    public void should_print_2_time() {
        SupplierPerson person = SupplierPerson.builder().build();
        Supplier<SupplierPerson> supplier = Suppliers.memoizeWithExpiration(person, 1, TimeUnit.NANOSECONDS);
        supplier.get();
        supplier.get();
        assertThat("get get ", is(content.toString()));
    }

}
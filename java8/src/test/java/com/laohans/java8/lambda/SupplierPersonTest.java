package com.laohans.java8.lambda;

import com.google.common.base.Suppliers;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SupplierPersonTest {
    @Before
    public void setUp() throws Exception {
        SupplierPerson.atomicInteger.set(0);
    }

    @Test
    public void should_print_1_time() {
        SupplierPerson person = SupplierPerson.builder().build();
        Supplier<SupplierPerson> supplier = Suppliers.memoizeWithExpiration(person, 1, TimeUnit.SECONDS);
        supplier.get();
        supplier.get();
        assertThat(SupplierPerson.atomicInteger.get(), is(1));
    }

    @Test
    public void should_print_2_time() {
        SupplierPerson person = SupplierPerson.builder().build();
        Supplier<SupplierPerson> supplier = Suppliers.memoizeWithExpiration(person, 1, TimeUnit.NANOSECONDS);
        supplier.get();
        supplier.get();
        assertThat(SupplierPerson.atomicInteger.get(), is(2));
    }

}
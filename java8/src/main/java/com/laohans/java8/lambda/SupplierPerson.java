package com.laohans.java8.lambda;

import com.google.common.base.Supplier;
import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;


@Builder
@Data
class SupplierPerson implements Supplier<SupplierPerson> {
    public static final AtomicInteger atomicInteger = new AtomicInteger();


    @Override
    public SupplierPerson get() {
        atomicInteger.incrementAndGet();
        return this;
    }
}

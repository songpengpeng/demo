package com.laohans.java8.lambda;

import com.google.common.base.Supplier;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
class SupplierPerson implements Supplier<SupplierPerson> {


    @Override
    public SupplierPerson get() {
        System.out.print("get ");
        return this;
    }
}

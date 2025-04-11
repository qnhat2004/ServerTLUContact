package com.server.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UnitTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Unit getUnitSample1() {
        return new Unit().id(1L).unitCode("unitCode1").name("name1").address("address1").logoUrl("logoUrl1").email("email1").fax("fax1");
    }

    public static Unit getUnitSample2() {
        return new Unit().id(2L).unitCode("unitCode2").name("name2").address("address2").logoUrl("logoUrl2").email("email2").fax("fax2");
    }

    public static Unit getUnitRandomSampleGenerator() {
        return new Unit()
            .id(longCount.incrementAndGet())
            .unitCode(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .logoUrl(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString());
    }
}

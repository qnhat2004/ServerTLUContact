package com.server.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StaffTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Staff getStaffSample1() {
        return new Staff()
            .id(1L)
            .staffId("staffId1")
            .fullName("fullName1")
            .position("position1")
            .phone("phone1")
            .address("address1")
            .education("education1");
    }

    public static Staff getStaffSample2() {
        return new Staff()
            .id(2L)
            .staffId("staffId2")
            .fullName("fullName2")
            .position("position2")
            .phone("phone2")
            .address("address2")
            .education("education2");
    }

    public static Staff getStaffRandomSampleGenerator() {
        return new Staff()
            .id(longCount.incrementAndGet())
            .staffId(UUID.randomUUID().toString())
            .fullName(UUID.randomUUID().toString())
            .position(UUID.randomUUID().toString())
            .phone(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .education(UUID.randomUUID().toString());
    }
}

package com.server.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StudentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Student getStudentSample1() {
        return new Student()
            .id(1L)
            .studentId("studentId1")
            .fullName("fullName1")
            .phone("phone1")
            .address("address1")
            .studyClass("studyClass1");
    }

    public static Student getStudentSample2() {
        return new Student()
            .id(2L)
            .studentId("studentId2")
            .fullName("fullName2")
            .phone("phone2")
            .address("address2")
            .studyClass("studyClass2");
    }

    public static Student getStudentRandomSampleGenerator() {
        return new Student()
            .id(longCount.incrementAndGet())
            .studentId(UUID.randomUUID().toString())
            .fullName(UUID.randomUUID().toString())
            .phone(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .studyClass(UUID.randomUUID().toString());
    }
}

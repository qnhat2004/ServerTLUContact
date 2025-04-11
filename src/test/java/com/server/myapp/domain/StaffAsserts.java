package com.server.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertStaffAllPropertiesEquals(Staff expected, Staff actual) {
        assertStaffAutoGeneratedPropertiesEquals(expected, actual);
        assertStaffAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertStaffAllUpdatablePropertiesEquals(Staff expected, Staff actual) {
        assertStaffUpdatableFieldsEquals(expected, actual);
        assertStaffUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertStaffAutoGeneratedPropertiesEquals(Staff expected, Staff actual) {
        assertThat(expected)
            .as("Verify Staff auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertStaffUpdatableFieldsEquals(Staff expected, Staff actual) {
        assertThat(expected)
            .as("Verify Staff relevant properties")
            .satisfies(e -> assertThat(e.getStaffId()).as("check staffId").isEqualTo(actual.getStaffId()))
            .satisfies(e -> assertThat(e.getFullName()).as("check fullName").isEqualTo(actual.getFullName()))
            .satisfies(e -> assertThat(e.getPosition()).as("check position").isEqualTo(actual.getPosition()))
            .satisfies(e -> assertThat(e.getPhone()).as("check phone").isEqualTo(actual.getPhone()))
            .satisfies(e -> assertThat(e.getAddress()).as("check address").isEqualTo(actual.getAddress()))
            .satisfies(e -> assertThat(e.getEducation()).as("check education").isEqualTo(actual.getEducation()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getAvatarUrl()).as("check avatarUrl").isEqualTo(actual.getAvatarUrl()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertStaffUpdatableRelationshipsEquals(Staff expected, Staff actual) {
        assertThat(expected)
            .as("Verify Staff relationships")
            .satisfies(e -> assertThat(e.getUnit()).as("check unit").isEqualTo(actual.getUnit()));
    }
}

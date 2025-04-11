package com.server.myapp.domain;

import static com.server.myapp.domain.StaffTestSamples.*;
import static com.server.myapp.domain.UnitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.server.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StaffTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Staff.class);
        Staff staff1 = getStaffSample1();
        Staff staff2 = new Staff();
        assertThat(staff1).isNotEqualTo(staff2);

        staff2.setId(staff1.getId());
        assertThat(staff1).isEqualTo(staff2);

        staff2 = getStaffSample2();
        assertThat(staff1).isNotEqualTo(staff2);
    }

    @Test
    void unitTest() {
        Staff staff = getStaffRandomSampleGenerator();
        Unit unitBack = getUnitRandomSampleGenerator();

        staff.setUnit(unitBack);
        assertThat(staff.getUnit()).isEqualTo(unitBack);

        staff.unit(null);
        assertThat(staff.getUnit()).isNull();
    }
}

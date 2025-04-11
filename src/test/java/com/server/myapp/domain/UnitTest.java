package com.server.myapp.domain;

import static com.server.myapp.domain.UnitTestSamples.*;
import static com.server.myapp.domain.UnitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.server.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Unit.class);
        Unit unit1 = getUnitSample1();
        Unit unit2 = new Unit();
        assertThat(unit1).isNotEqualTo(unit2);

        unit2.setId(unit1.getId());
        assertThat(unit1).isEqualTo(unit2);

        unit2 = getUnitSample2();
        assertThat(unit1).isNotEqualTo(unit2);
    }

    @Test
    void parentUnitTest() {
        Unit unit = getUnitRandomSampleGenerator();
        Unit unitBack = getUnitRandomSampleGenerator();

        unit.setParentUnit(unitBack);
        assertThat(unit.getParentUnit()).isEqualTo(unitBack);

        unit.parentUnit(null);
        assertThat(unit.getParentUnit()).isNull();
    }
}

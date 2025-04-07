package com.server.myapp.domain;

import static com.server.myapp.domain.StudentTestSamples.*;
import static com.server.myapp.domain.UnitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.server.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = getStudentSample1();
        Student student2 = new Student();
        assertThat(student1).isNotEqualTo(student2);

        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);

        student2 = getStudentSample2();
        assertThat(student1).isNotEqualTo(student2);
    }

    @Test
    void unitTest() {
        Student student = getStudentRandomSampleGenerator();
        Unit unitBack = getUnitRandomSampleGenerator();

        student.setUnit(unitBack);
        assertThat(student.getUnit()).isEqualTo(unitBack);

        student.unit(null);
        assertThat(student.getUnit()).isNull();
    }
}

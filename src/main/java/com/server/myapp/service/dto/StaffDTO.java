package com.server.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.server.myapp.domain.Staff} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StaffDTO implements Serializable {

    private Long id;

    @NotNull
    private String staffId;

    @NotNull
    private String fullName;

    private String position;

    private String phone;

    private String address;

    private String education;

    private String email;

    private UserDTO user;

    private UnitDTO unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StaffDTO)) {
            return false;
        }

        StaffDTO staffDTO = (StaffDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, staffDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StaffDTO{" +
            "id=" + getId() +
            ", staffId='" + getStaffId() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", position='" + getPosition() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", education='" + getEducation() + "'" +
            ", email='" + getEmail() + "'" +
            ", user=" + getUser() +
            ", unit=" + getUnit() +
            "}";
    }
}

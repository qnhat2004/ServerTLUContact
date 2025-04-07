package com.server.myapp.service.dto;

import com.server.myapp.domain.enumeration.UnitType;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.server.myapp.domain.Unit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnitDTO implements Serializable {

    private Long id;

    @NotNull
    private String unitCode;

    @NotNull
    private String name;

    private String address;

    private String logoUrl;

    private String email;

    private String fax;

    @NotNull
    private UnitType type;

    private UnitDTO parentUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public UnitType getType() {
        return type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public UnitDTO getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(UnitDTO parentUnit) {
        this.parentUnit = parentUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitDTO)) {
            return false;
        }

        UnitDTO unitDTO = (UnitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, unitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitDTO{" +
            "id=" + getId() +
            ", unitCode='" + getUnitCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", type='" + getType() + "'" +
            ", parentUnit=" + getParentUnit() +
            "}";
    }
}

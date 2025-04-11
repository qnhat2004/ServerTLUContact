package com.server.myapp.service.dto;

import com.server.myapp.domain.enumeration.UnitType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for detailed information of the {@link com.server.myapp.domain.Unit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnitDetailDTO implements Serializable {

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

    private Long parentUnitId; // Chỉ lưu ID của đơn vị cha

    private List<Long> childUnitIds = new ArrayList<>(); // Chỉ lưu danh sách ID của đơn vị con

    // Getters and setters
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

    public Long getParentUnitId() {
        return parentUnitId;
    }

    public void setParentUnitId(Long parentUnitId) {
        this.parentUnitId = parentUnitId;
    }

    public List<Long> getChildUnitIds() {
        return childUnitIds;
    }

    public void setChildUnitIds(List<Long> childUnitIds) {
        this.childUnitIds = childUnitIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitDetailDTO)) {
            return false;
        }

        UnitDetailDTO unitDetailDTO = (UnitDetailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, unitDetailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "UnitDetailDTO{" +
            "id=" + getId() +
            ", unitCode='" + getUnitCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", type='" + getType() + "'" +
            ", parentUnitId=" + getParentUnitId() +
            ", childUnitIds=" + getChildUnitIds() +
            "}";
    }
}

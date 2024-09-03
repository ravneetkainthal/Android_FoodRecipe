package com.property.manager.model;

import java.util.Date;
import java.util.UUID;

public class Property {

    private UUID propertyId;
    private String propertyName;
    private String propertyDescription;
    private Date availabilityDate;

    public Property() {
        propertyId = UUID.randomUUID();
        availabilityDate = new Date();
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }
}

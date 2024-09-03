package com.property.manager.lab;

import android.content.Context;

import com.property.manager.model.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropertyLab {

    private static PropertyLab sPropertyLab;
    private List<Property> mProperties;
    public static PropertyLab get() {
        if (sPropertyLab == null) {
            sPropertyLab = new PropertyLab();
        }
        return sPropertyLab;
    }
    private PropertyLab() {
        mProperties = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Property property = new Property();
            property.setPropertyName("Property Name #" + i);
            property.setPropertyDescription("Property Description #" + i);
            mProperties.add(property);
        }
    }

    public List<Property> getProperties() {
        return mProperties;
    }
    public Property getProperty(UUID id) {
        for (Property property : mProperties) {
            if (property.getPropertyId().equals(id)) {
                return property;
            }
        }
        return null;
    }

}

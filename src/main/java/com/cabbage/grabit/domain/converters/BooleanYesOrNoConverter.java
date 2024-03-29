package com.cabbage.grabit.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply = true)
public class BooleanYesOrNoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute?"Y":"N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData.equals("Y");
    }

}

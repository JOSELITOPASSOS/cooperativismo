package com.act.cooperativism.domain.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;

public class BooleanSimNaoConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean atributo) {
		if(Objects.nonNull(atributo)) {
			return atributo.booleanValue() ? "Sim" : "Não";
		}
		return "Não";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		if ("Sim".equals(dbData)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
	}

}

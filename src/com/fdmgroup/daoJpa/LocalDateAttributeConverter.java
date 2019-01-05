package com.fdmgroup.daoJpa;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate,Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		// TODO Auto-generated method stub
		return (localDate == null ? null : Date.valueOf(localDate));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbDate) {
		// TODO Auto-generated method stub
		return (dbDate == null ? null : dbDate.toLocalDate());
	}

}

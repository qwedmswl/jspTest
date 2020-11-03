package kr.or.ddit.config.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateStringConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = transFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	

}

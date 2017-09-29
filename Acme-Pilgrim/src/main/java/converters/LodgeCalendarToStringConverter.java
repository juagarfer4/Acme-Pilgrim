package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LodgeCalendar;

@Component
@Transactional
public class LodgeCalendarToStringConverter implements Converter<LodgeCalendar, String>{
	
	@Override
	public String convert(LodgeCalendar lodgeCalendar){
		String result;
		
		if(lodgeCalendar == null)
			result = null;
		
		else
			result = String.valueOf(lodgeCalendar.getId());
		
		return result;
	}

}

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LodgeCalendarRepository;

import domain.LodgeCalendar;

@Component
@Transactional
public class StringToLodgeCalendarConverter implements Converter<String, LodgeCalendar>{
	
	@Autowired
	LodgeCalendarRepository lodgeCalendarRepository;
	
	@Override
	public LodgeCalendar convert(String text){
		LodgeCalendar result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = lodgeCalendarRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

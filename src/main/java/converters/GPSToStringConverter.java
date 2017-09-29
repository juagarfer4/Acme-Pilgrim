package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.GPS;

@Component
@Transactional
public class GPSToStringConverter implements Converter<GPS, String>{
	
	@Override
	public String convert(GPS gPS){
		String result;
		
		if(gPS == null)
			result = null;
		
		else
			result = String.valueOf(gPS.getId());
		
		return result;
	}

}

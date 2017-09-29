package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.GPSRepository;

import domain.GPS;

@Component
@Transactional
public class StringToGPSConverter implements Converter<String, GPS>{
	
	@Autowired
	GPSRepository gPSRepository;
	
	@Override
	public GPS convert(String text){
		GPS result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = gPSRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

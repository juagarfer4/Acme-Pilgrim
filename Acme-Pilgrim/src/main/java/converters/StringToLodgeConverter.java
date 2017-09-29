package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LodgeRepository;

import domain.Lodge;

@Component
@Transactional
public class StringToLodgeConverter implements Converter<String, Lodge>{
	
	@Autowired
	LodgeRepository lodgeRepository;
	
	@Override
	public Lodge convert(String text){
		Lodge result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = lodgeRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

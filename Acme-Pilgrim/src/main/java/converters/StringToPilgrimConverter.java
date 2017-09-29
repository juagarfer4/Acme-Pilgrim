package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PilgrimRepository;

import domain.Pilgrim;

@Component
@Transactional
public class StringToPilgrimConverter implements Converter<String, Pilgrim>{
	
	@Autowired
	PilgrimRepository pilgrimRepository;
	
	@Override
	public Pilgrim convert(String text){
		Pilgrim result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = pilgrimRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

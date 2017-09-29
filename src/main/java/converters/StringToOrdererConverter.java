package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.OrdererRepository;

import domain.Orderer;

@Component
@Transactional
public class StringToOrdererConverter implements Converter<String, Orderer>{
	
	@Autowired
	OrdererRepository ordererRepository;
	
	@Override
	public Orderer convert(String text){
		Orderer result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = ordererRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

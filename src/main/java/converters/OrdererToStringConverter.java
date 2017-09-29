package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Orderer;

@Component
@Transactional
public class OrdererToStringConverter implements Converter<Orderer, String>{
	
	@Override
	public String convert(Orderer orderer){
		String result;
		
		if(orderer == null)
			result = null;
		
		else
			result = String.valueOf(orderer.getId());
		
		return result;
	}

}

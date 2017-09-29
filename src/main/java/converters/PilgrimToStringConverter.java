package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Pilgrim;

@Component
@Transactional
public class PilgrimToStringConverter implements Converter<Pilgrim, String>{
	
	@Override
	public String convert(Pilgrim pilgrim){
		String result;
		
		if(pilgrim == null)
			result = null;
		
		else
			result = String.valueOf(pilgrim.getId());
		
		return result;
	}

}

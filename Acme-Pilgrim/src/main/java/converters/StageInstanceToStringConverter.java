package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StageInstance;

@Component
@Transactional
public class StageInstanceToStringConverter implements Converter<StageInstance, String>{
	
	@Override
	public String convert(StageInstance stageInstance){
		String result;
		
		if(stageInstance == null)
			result = null;
		
		else
			result = String.valueOf(stageInstance.getId());
		
		return result;
	}

}

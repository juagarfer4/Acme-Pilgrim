package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LodgeAssessment;

@Component
@Transactional
public class LodgeAssessmentToStringConverter implements Converter<LodgeAssessment, String>{
	
	@Override
	public String convert(LodgeAssessment lodgeAssessment){
		String result;
		
		if(lodgeAssessment == null)
			result = null;
		
		else
			result = String.valueOf(lodgeAssessment.getId());
		
		return result;
	}

}

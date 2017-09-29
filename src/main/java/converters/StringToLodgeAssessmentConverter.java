package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LodgeAssessmentRepository;

import domain.LodgeAssessment;

@Component
@Transactional
public class StringToLodgeAssessmentConverter implements Converter<String, LodgeAssessment>{
	
	@Autowired
	LodgeAssessmentRepository lodgeAssessmentRepository;
	
	@Override
	public LodgeAssessment convert(String text){
		LodgeAssessment result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = lodgeAssessmentRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

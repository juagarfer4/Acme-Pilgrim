package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.StageInstanceRepository;

import domain.StageInstance;

@Component
@Transactional
public class StringToStageInstanceConverter implements Converter<String, StageInstance>{
	
	@Autowired
	StageInstanceRepository stageInstanceRepository;
	
	@Override
	public StageInstance convert(String text){
		StageInstance result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = stageInstanceRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

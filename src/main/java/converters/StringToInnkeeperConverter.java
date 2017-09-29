package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.InnkeeperRepository;

import domain.Innkeeper;

@Component
@Transactional
public class StringToInnkeeperConverter implements Converter<String, Innkeeper>{
	
	@Autowired
	InnkeeperRepository innkeeperRepository;
	
	@Override
	public Innkeeper convert(String text){
		Innkeeper result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = innkeeperRepository.findOne(id);
			}
		}catch (Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

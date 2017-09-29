package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;

import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {
	
	// Managed repository ----------------------------
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	// Simple CRUD methods -----------------------------
	
	public void save(CreditCard creditCard){
		Assert.notNull(creditCard);
		
		creditCardRepository.save(creditCard);
	}
	
	// Other business methods ----------------------------

}

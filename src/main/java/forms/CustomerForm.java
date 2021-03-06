package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.CreditCard;

public class CustomerForm extends ActorForm{
	
	private CreditCard creditCard;
	
	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}

package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Orderer;
import domain.Route;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrdererServiceNegativeTest extends AbstractTest{
	
	// Services under test --------------------------------------
	
	@Autowired
	private OrdererService ordererService;
	
	@Autowired
	private RouteService routeService;
	
	// Test ---------------------------------------------------
	
	// Asignar una etapa a una ruta inexistente. -------------------------
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testNegativeCreateOrderer(){
		authenticate("administrator1");
		
		Orderer orderer;
		Route route;
		
		orderer=ordererService.create(27);
		route=routeService.findOne(43);
		
		orderer.setRoute(route);
		
		ordererService.save(orderer);
		
		unauthenticate();
	}

}

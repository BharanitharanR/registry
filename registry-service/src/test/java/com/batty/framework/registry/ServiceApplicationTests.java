package com.batty.framework.registry;

import com.batty.registry.api.RegistryApi;
import com.batty.registry.model.ServiceSchema;
import com.batty.registry.registry.RegisterService;
import net.jodah.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.batty.registry.RestService;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

// By default JUNIts are executed randonly
// Hence to make them get executed in the order attempted it to be ordered by the order ID.
// Is this good or bad?
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceApplicationTests {


	@Autowired
	protected RestService service;


	@Test
	@Order(1)
	public void testAddService() {
		if(service != null )
		{
			ServiceSchema schema = new ServiceSchema();
			schema.setServiceId("TestService");
			schema.setServiceName("TestService");
			schema.setStatus("true");
			schema.setServiceHostIP("testHostIP");
			ResponseEntity<ServiceSchema> resp =  service.addService("testService",schema);
			Assertions.assertEquals(200 , resp.getStatusCode().value());
		}
	}

	@Test
	@Order(2)
	public void tryAddingAnExistingService() {
		if(service != null )
		{
			ServiceSchema schema = new ServiceSchema();
			schema.setServiceId("TestService");
			schema.setServiceName("TestService");
			schema.setStatus("true");
			schema.setServiceHostIP("testHostIP");
			ResponseEntity<ServiceSchema> resp =  service.addService("testService",schema);
			Assertions.assertNotEquals(200 , resp.getStatusCode().value());
		}
	}


	@Test
	@Order(3)
	public void testGetService() {
		if(service != null)
		{
			ResponseEntity<ServiceSchema> resp = service.getService("TestService");
			Assertions.assertEquals("true" , resp.getBody().getStatus());
		}
	}


}

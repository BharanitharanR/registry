package com.batty.framework.registry;

// import com.batty.service.api.DefaultApi;
// import com.batty.service.api.client.ApiException;
import com.batty.registry.api.DefaultApi;
import com.batty.registry.api.client.ApiClient;
import com.batty.registry.api.client.ApiException;
import com.batty.registry.api.model.ServiceSchema;
import org.junit.jupiter.api.Test;


class ServiceApplicationTests {


    protected DefaultApi api;

	@Test
	void contextLoads()  {
		try {
			com.batty.registry.api.client.Configuration.setDefaultApiClient(new ApiClient().setBasePath("http://localhost:8080"));
			DefaultApi api = new DefaultApi();
			ServiceSchema model = new ServiceSchema();
			model.setServiceId("testService");
			model.setServiceHostIP("localhost");
			model.setServiceName("serviceTest");
			model.setStatus("Online");
			api.addService("testService", model);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}



}

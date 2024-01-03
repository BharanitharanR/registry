package com.batty.registry.registry;

import com.batty.framework.interfaces.RegisterServiceInterface;
import com.batty.registry.RestService;
import com.batty.registry.datastore.DatastoreImpl;
import com.batty.registry.model.ServiceSchema;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("RegisterService")
public class RegisterService implements RegisterServiceInterface {

    @Autowired
    protected RestService service;

    @PostConstruct
    public void register()
    {

        registerService(true);
    }

    @PreDestroy
    public void unregister()
    {
        registerService(false);
    }

    @Override
    public void registerService(boolean value) {
        ServiceSchema schema = new ServiceSchema();
        schema.setServiceId("Register");
        schema.setServiceName("Register");
        schema.setServiceHostIP("localhost");
        if( value ) { schema.setStatus("Ok"); } else { schema.setStatus("Off"); };
        this.service.addService("Register",schema);
    }
}

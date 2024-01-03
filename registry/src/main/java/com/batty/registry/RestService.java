package com.batty.registry;

import com.batty.registry.api.RegistryApi;
import com.batty.registry.model.ServiceSchema;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Component("RegistryController")
@RestController
public class RestService implements RegistryApi
{
  // https://mydeveloperplanet.com/2022/02/08/generate-server-code-using-openapi-generator/

  // https://github.com/mydeveloperplanet/myopenapiplanet/tree/master

    @Override
    public ResponseEntity<ServiceSchema> addService(String serviceId, ServiceSchema serviceSchema) {
        return null;
    }

    @Override
    public ResponseEntity<ServiceSchema> getService(String serviceId) {
        return null;
    }
}

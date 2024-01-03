package com.batty.registry;

import com.batty.registry.api.RegistryApi;
import com.batty.registry.datastore.DatastoreImpl;
import com.batty.registry.model.Error;
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
    @Autowired
    protected DatastoreImpl datastore;

    @Override
    public ResponseEntity<ServiceSchema> addService(String serviceId, ServiceSchema serviceSchema) {
      try {
        return ResponseEntity.ok(datastore.addService(serviceSchema));
      }
      catch(Exception e)
      {

        return (ResponseEntity<ServiceSchema>) ResponseEntity.internalServerError();
      }
    }

    @Override
    public ResponseEntity<ServiceSchema> getService(String serviceId) {
      try {
        return ResponseEntity.ok(datastore.findServiceById(serviceId));
      }
      catch(Exception e)
      {
        return (ResponseEntity<ServiceSchema>) ResponseEntity.internalServerError();
      }
    }
}

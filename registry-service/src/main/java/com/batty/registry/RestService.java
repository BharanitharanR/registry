package com.batty.registry;

import com.batty.registry.api.RegistryApi;
import com.batty.registry.model.DeleteServiceSchema;
import com.batty.registry.datastore.DatastoreImpl;
import com.batty.registry.model.ServiceSchema;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.batty.registry.RestServiceGrpc.*;



@Component("RegistryController")
@RestController
@GrpcService
public class RestService extends RestServiceImplBase implements RegistryApi  {
  // https://mydeveloperplanet.com/2022/02/08/generate-server-code-using-openapi-generator/

  // https://github.com/mydeveloperplanet/myopenapiplanet/tree/master
    @Autowired
    protected DatastoreImpl datastore;

    @Override
    public ResponseEntity<ServiceSchema> addService(String serviceId, ServiceSchema serviceSchema) {
      try
      {
          if( datastore.addService(serviceSchema) )
          {
              return ResponseEntity.ok(datastore.findServiceById(serviceSchema.getServiceId()));
          }
          else {
              return ResponseEntity.noContent().build();
          }
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

    @Override
    public ResponseEntity<ServiceSchema> registerHeartbeat(String serviceId, ServiceSchema serviceSchema) {
        if( datastore.addService(serviceSchema) )
        {
            return ResponseEntity.ok(datastore.findServiceById(serviceSchema.getServiceId()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<DeleteServiceSchema> removeService(String serviceId) {
        try
        {

            return ResponseEntity.ok(datastore.deleteServiceById(serviceId));
        }
        catch(Exception e)
        {
            return (ResponseEntity<DeleteServiceSchema>) ResponseEntity.internalServerError();
        }
    }

    public void registerService(registerServiceRequest request, StreamObserver<registerServiceResponse> responseObserver) {
      ServiceSchema schema = new ServiceSchema();
      schema.setStatus(Boolean.toString(request.getStatus()));
      schema.setServiceId(request.getServiceName());
      schema.setServiceName(request.getServiceName());
      schema.setServiceHostIP(request.getIp());
      registerServiceResponse reply = registerServiceResponse.getDefaultInstance();

      if ( datastore.addService(schema) )
      {
           schema =  datastore.findServiceById(schema.getServiceId());
          reply = registerServiceResponse.newBuilder()
                  .setServiceName(schema.getServiceName()).setIp(schema.getServiceHostIP()).setStatus(Boolean.getBoolean(schema.getStatus()))
                  .build();
      }
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}

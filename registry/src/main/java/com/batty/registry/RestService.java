package com.batty.registry;

import com.batty.registry.api.RegistryApi;
import com.batty.registry.datastore.DatastoreImpl;
import com.batty.registry.model.Error;
import com.batty.registry.model.MongoDate;
import com.batty.registry.model.ServiceSchema;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.batty.registry.RestServiceGrpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


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



  @Override
  public void registerService(registerServiceRequest request, StreamObserver<registerServiceResponse> responseObserver) {
      ServiceSchema schema = new ServiceSchema();
      schema.setStatus(Boolean.toString(request.getStatus()));
      schema.setServiceId(request.getServiceName());
      schema.setServiceName(request.getServiceName());
      schema.setServiceHostIP(request.getIp());


      schema = datastore.addService(schema);
      registerServiceResponse reply = registerServiceResponse.newBuilder()
            .setServiceName(schema.getServiceName()).setIp(schema.getServiceHostIP()).setStatus(Boolean.getBoolean(schema.getStatus()))
            .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}

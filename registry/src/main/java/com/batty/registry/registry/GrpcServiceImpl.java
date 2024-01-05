package com.batty.registry.registry;

import com.batty.registry.HelloRequest;
import com.batty.registry.HelloResponse;
import com.batty.registry.RestServiceGrpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

// @GrpcService
public class GrpcServiceImpl { //extends RestServiceImplBase {
/*
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse reply = HelloResponse.newBuilder()
                .setResp("Hello ==> " + request.getFirstName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }*/
}

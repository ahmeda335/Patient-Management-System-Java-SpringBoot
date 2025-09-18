package com.example.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount (BillingRequest billingRequest,
                                      StreamObserver<BillingResponse> responseObserver){
        log.info("Accepted Billing Request from createBillingAccount {} ", billingRequest.toString());
        // Business logic e.g. save to database and calculations.

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("1234")
                .setStatus("PENDING")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

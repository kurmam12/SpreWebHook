package com.de.le.sprewebhook.service;

import com.de.le.sprewebhook.model.PayloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class WebhookService {
    @Autowired
    ObjectMapper objectMapper;
    public void processWebHook(String payload){
        PayloadModel payloadModelRequests = objectMapper.readValue(payload,PayloadModel.class);
        switch(payloadModelRequests.getEvent()){
            case "payment.success":
                handlePaymentSuccess(payloadModelRequests);
            case "payment.failed":
                handlePaymentFailed(payloadModelRequests);
            default:
                System.out.println("Unknown event");


        }

    }

    private void handlePaymentFailed(PayloadModel payloadModelRequests) {
        System.out.println("Payment successful"+payloadModelRequests.getOrderId());
    }

    private void handlePaymentSuccess(PayloadModel payloadModelRequests) {
        System.out.println("Payment Failed"+payloadModelRequests.getOrderId());

    }
}

package com.de.le.sprewebhook.controller;

import com.de.le.sprewebhook.compo.PayloadCompos;
import com.de.le.sprewebhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebHookController {
    @Autowired
    private PayloadCompos payloadCompos;
    @Autowired
    WebhookService webHookService;
    @PostMapping("/payment")
    public ResponseEntity<String> handleWebHook(@RequestHeader("X-Signature")String signature,@RequestBody String payload){
        System.out.println("Controller Hit start");
        if(!payloadCompos.isValid(payload,signature)){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Signature");
    }
    webHookService.processWebHook(payload);
        System.out.println("Controller Hit End");
    return ResponseEntity.ok("webhook processed");
    }


}

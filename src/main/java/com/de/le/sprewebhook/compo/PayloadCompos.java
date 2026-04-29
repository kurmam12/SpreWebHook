package com.de.le.sprewebhook.compo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Component
public class PayloadCompos {
    private static final String SECRET="my-mysecret-fnal";
    public boolean isValid(String payload,String signature){
        try {
            Mac myMac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
            myMac.init(key);
            byte[] rawHmac = myMac.doFinal(payload.getBytes());
            String generatedSignature = Base64.getEncoder().encodeToString(rawHmac);
            log.info("The generated signature is ---->"+generatedSignature);
            return generatedSignature.equals(signature);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

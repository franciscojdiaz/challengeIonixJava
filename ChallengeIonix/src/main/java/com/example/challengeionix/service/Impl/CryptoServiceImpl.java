package com.example.challengeionix.service.Impl;

import com.example.challengeionix.service.ICryptoService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;

@Service
public class CryptoServiceImpl implements ICryptoService {

    private String key = "ionix123456";
    @Override
    public String cryptoParam(String param) {


        try {

            String algorithm = "DES";
            KeySpec ks = new DESKeySpec(key.getBytes("UTF8"));
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey sky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance(algorithm);

            cf.init(Cipher.ENCRYPT_MODE, sky);

            byte[] encrypted = cf.doFinal(param.getBytes());

            return Base64.encodeBase64String(encrypted);

        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
}

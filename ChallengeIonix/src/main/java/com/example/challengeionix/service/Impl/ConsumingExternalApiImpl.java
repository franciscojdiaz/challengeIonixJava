package com.example.challengeionix.service.Impl;

import com.example.challengeionix.service.IConsumingApiService;
import com.example.challengeionix.service.ICryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ConsumingExternalApiImpl implements IConsumingApiService {

    @Autowired
    private ICryptoService cryptoService;

    private String uri =  "https://my.api.mockaroo.com/test-tecnico/search/";

    @Override
    public String consumingExternalApi(String param) {
        String paramcrypto = cryptoService.cryptoParam(param);
        String URL = uri + paramcrypto;

        WebClient.Builder builder = WebClient.builder();
        String callresp = builder
                .build()
                .get()
                .uri(URL)
                .header("X-API-Key","f2f719e0")
                .retrieve()
                .bodyToMono(String.class).block();
        return callresp;
    }
}

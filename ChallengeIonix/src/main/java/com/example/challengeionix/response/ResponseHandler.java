package com.example.challengeionix.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, String elapsetime, Object responseObj,int Rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("elapsetime", elapsetime +" ms");
        map.put("data", responseObj);
        map.put("rows", Rows);


        return new ResponseEntity<Object>(map,status);
    }
}

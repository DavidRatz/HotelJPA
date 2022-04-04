package bstorm.akimts.correction_jpa.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo/headers")
    public ResponseEntity<?> getHeaders(@RequestHeader HttpHeaders headers){

        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/demo/header")
    public ResponseEntity<?> getHeaders(@RequestHeader(HttpHeaders.HOST) String host){
        System.out.println(host);
        
        return ResponseEntity.ok().build();
    }
}

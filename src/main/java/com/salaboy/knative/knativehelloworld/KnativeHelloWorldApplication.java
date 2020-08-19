package com.salaboy.knative.knativehelloworld;


import com.salaboy.cloudevents.helper.CloudEventsHelper;
import io.cloudevents.CloudEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@SpringBootApplication
@RestController
@Slf4j
public class KnativeHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnativeHelloWorldApplication.class, args);
    }

    private final static Logger logger = Logger.getLogger(KnativeHelloWorldApplication.class.getName());


    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {

        return "Hello there Knative Traveller: " + name;

    }


    @PostMapping
    public String recieveCloudEvent(@RequestHeader HttpHeaders headers, @RequestBody Object body) {
        CloudEvent cloudEvent = CloudEventsHelper.parseFromRequest(headers, body);
        System.out.println("> I got a cloud event: " + cloudEvent.toString());
        for(String key : cloudEvent.getAttributeNames())
        System.out.println("  -> cloud event attr ("+key+"): " + cloudEvent.getAttribute(key));

        System.out.println("  -> cloud event data: " + cloudEvent.getData());

        return "OK!";
    }

    @PostMapping("/another")
    public String recieveCloudEventAnother(@RequestHeader HttpHeaders headers, @RequestBody Object body) {
        CloudEvent cloudEvent = CloudEventsHelper.parseFromRequest(headers, body);
        System.out.println("> I got a cloud event: " + cloudEvent.toString());
        for(String key : cloudEvent.getAttributeNames())
            System.out.println("  -> cloud event attr ("+key+"): " + cloudEvent.getAttribute(key));

        System.out.println("  -> cloud event data: " + cloudEvent.getData());

        return "OK!";
    }


}

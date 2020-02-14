package com.salaboy.knative.knativehelloworld;

import com.salaboy.cloudevents.helper.CloudEventsHelper;
import io.cloudevents.CloudEvent;
import io.cloudevents.v03.AttributesImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
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
    public String recieveCloudEvent(@RequestHeader Map<String, String> headers, @RequestBody Object body) {
        CloudEvent<AttributesImpl, String> cloudEvent = CloudEventsHelper.parseFromRequest(headers, body);
        System.out.println("> I got a cloud event: " + cloudEvent.toString());
        System.out.println("  -> cloud event attr: " + cloudEvent.getAttributes());
        System.out.println("  -> cloud event data: " + cloudEvent.getData());

        return "OK!";
    }


}

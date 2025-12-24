package net.hajar.kafkaspringcloudstream.controllers;

import net.hajar.kafkaspringcloudstream.events.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@RestController
public class PageEventController {
    @Autowired
    private StreamBridge streamBridge;   // permet de publier un message sur un broker

    @GetMapping("/publish")
    public PageEvent publish(String name, String topic) {
        PageEvent event = new PageEvent(
                name,
                Math.random()>0.5?"U1":"U2",
                new Date(),
                10 + new Random().nextLong(10000));
        streamBridge.send(topic, event);
        return  event;
    }
}

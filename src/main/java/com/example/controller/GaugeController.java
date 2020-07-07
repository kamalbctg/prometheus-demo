package com.example.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaugeController {
    private final Gauge queueSize;

    public GaugeController(CollectorRegistry collectorRegistry) {
        queueSize = Gauge.build()
                .name("queue_size")
                .help("Size of queue.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/gauge/push")
    public String push() {
        queueSize.inc();
        return "pushed";
    }

    @GetMapping(value = "/guage/pop")
    public String pop() {
        queueSize.dec();
        return "Popped";
    }
}

package com.geek.orange.product.site.sample;

import com.geek.orange.product.ProductApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SinkReceiver {

    private static final Logger loggger = LoggerFactory.getLogger(ProductApplication.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        loggger.info("Received: " + payload);
        System.err.println("Received: " + payload);
    }
}

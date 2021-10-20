package com.jetbone.others.flow;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Chris
 * @date 2021-10-20
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws Exception {

        var publisher = new TestPublisher();
        var middleProcessor = new TestMiddleProcessor();
        var endProcessor = new TestEndProcessor();


        publisher.subscribe(middleProcessor);
        middleProcessor.subscribe(endProcessor);

        List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").forEach(publisher::submit);
        publisher.close();

        Thread.sleep(15000);


        log.info("-------- End --------");

    }
}

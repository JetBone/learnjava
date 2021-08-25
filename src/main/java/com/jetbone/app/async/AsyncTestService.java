package com.jetbone.app.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Chris
 * @date 2021-08-23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncTestService {

    private final AsyncService asyncService;
    private final AsyncService2 asyncService2;

    public void testSync(String input) {
        try {

            var result1 = asyncService.sync1(input);
            var result2 = asyncService.sync2(input);
            var result3 = asyncService.sync3(input);
            var result4 = asyncService.sync4(input);

            log.info("结果1：" + result1);
            log.info("结果2：" + result2);
            log.info("结果3：" + result3);
            log.info("结果4：" + result4);

            log.info("当前线程：" + Thread.currentThread().getName() + " , test sync end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

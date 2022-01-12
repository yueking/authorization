package com.yueking.security.demo.async;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Data
@Component
public class MockQueue {
    private String placeOrder;
    private String completeOrder;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setPlaceOrder(String placeOrder) {
        new Thread(()->{
            logger.info("接收下单请求:"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单处理完毕:"+placeOrder);
        }).start();
    }
}

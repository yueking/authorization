package com.yueking.security.demo.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * springboot 容器初始化后执行的方法
     *
     * @param event
     */

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("启动队列处理线程");
        new Thread(() -> {
            while (true) {
                String orderId = mockQueue.getCompleteOrder();
                if (StringUtils.isNotBlank(orderId)) {
                    logger.info("返回订单处理结果:" + orderId);
                    deferredResultHolder.getMap().get(orderId).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

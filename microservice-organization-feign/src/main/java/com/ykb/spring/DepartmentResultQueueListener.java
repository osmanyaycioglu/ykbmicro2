package com.ykb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DepartmentResultQueueListener {

    private static Logger logger = LoggerFactory.getLogger(DepartmentResultQueueListener.class);

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "department_result_queue",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "department_result_q_de",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "department_result_key"))
    public void listenDepartmentQueue(final String str) {
        if (DepartmentResultQueueListener.logger.isInfoEnabled()) {
            DepartmentResultQueueListener.logger.info("[DepartmentQueueListener][listenDepartmentQueue]-> " + str);
        }
    }

}

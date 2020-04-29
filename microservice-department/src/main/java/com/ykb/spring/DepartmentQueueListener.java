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
public class DepartmentQueueListener {

    private static Logger logger = LoggerFactory.getLogger(DepartmentQueueListener.class);

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "department_queue",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "dq_direct_exchange",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "department_key"))
    public void listenDepartmentQueue(final Department departmentParam) {
        if (DepartmentQueueListener.logger.isInfoEnabled()) {
            DepartmentQueueListener.logger.info("[DepartmentQueueListener][listenDepartmentQueue]-> "
                                                + departmentParam);
        }
    }

}

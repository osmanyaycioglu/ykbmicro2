package com.ykb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class DepartmentQueueListener {

    private static Logger logger = LoggerFactory.getLogger(DepartmentQueueListener.class);

    @Value("${server.port}")
    private int           port;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "department_queue",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "dq_direct_exchange",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "department_key"))
    @SendTo("department_result_q_de/department_result_key")
    public String listenDepartmentQueue(final Department departmentParam) {
        if (DepartmentQueueListener.logger.isInfoEnabled()) {
            DepartmentQueueListener.logger.info("[DepartmentQueueListener][listenDepartmentQueue]-> "
                                                + departmentParam);
        }
        return "Department Request prossed by " + this.port;
    }

}

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
public class EmployeeQueueListener {

    private static Logger logger = LoggerFactory.getLogger(EmployeeQueueListener.class);

    @Value("${server.port}")
    private int           port;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "employee_queue_1",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "employee_q_topic_e",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "employee.*.created"))
    @SendTo("department_result_q_de/department_result_key")
    public String listenEmployeeQueue1(final Employee employeeParam) {
        if (EmployeeQueueListener.logger.isInfoEnabled()) {
            EmployeeQueueListener.logger.info("[EmployeeQueueListener][listenDepartmentQueue]-> " + employeeParam);
        }

        return "Employee [1] Request processed by " + this.port;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "employee_queue_2",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "employee_q_topic_e",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "employee.*.*"))
    @SendTo("department_result_q_de/department_result_key")
    public String listenDepartmentQueue2(final Employee employeeParam) {
        if (EmployeeQueueListener.logger.isInfoEnabled()) {
            EmployeeQueueListener.logger.info("[EmployeeQueueListener][listenDepartmentQueue]-> " + employeeParam);
        }

        return "Employee Request processed by " + this.port;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "employee_queue_2",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(name = "employee_q_topic_e",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "employee.*.*"))

    @SendTo("department_result_q_de/department_result_key")
    public String listenDepartmentQueue3(final Employee employeeParam) {
        if (EmployeeQueueListener.logger.isInfoEnabled()) {
            EmployeeQueueListener.logger.info("[EmployeeQueueListener][listenDepartmentQueue]-> " + employeeParam);
        }

        return "Employee Request processed by " + this.port;
    }

}

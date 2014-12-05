package com.springapp.mvc;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@ContextConfiguration(locations = {"classpath*:spring/system/*-context.xml","file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class JmsPost {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    static
    TestService testService;

    @Autowired
    static
    JmsTemplate myJmsTemplate;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    public static void main(String[] args) {
        try {
            List<String> orderIDs = testService.getOrderIDs();
            System.out.println("OK");

            /*myJmsTemplate.send(
                    new MessageCreator() {
                        public ObjectMessage createMessage(Session session) throws JMSException {
                            ObjectMessage message = session.createObjectMessage();
                            message.setObject("My first Message");
                            return message;
                        }
                    });

            Message receivedMessage = myJmsTemplate.receive("vfuk.logistics.order.request.error");
            ObjectMessage msg = (ObjectMessage) receivedMessage;
            System.out.println("Message Received :" + msg.getObject().toString());*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

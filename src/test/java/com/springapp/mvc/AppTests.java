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
public class AppTests {
    private MockMvc mockMvc;

    final static short x = 2;
    public static int y = 0;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    TestService testService;

    @Autowired
    JmsTemplate myJmsTemplate;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }

    @Test
    public void staticTest() throws Exception {
        Song mysong = new Song();
        List<Song> songsList = testService.getSongsList();
        for (int i = 0; i < songsList.size(); i++) {
             System.out.println(songsList.get(i).getNumberOfSongs() + "--" + songsList.get(i).getId() + "--" + songsList.get(i).getName());
        }

        String s = "abbcccddddcccbba";
        char previous = '\0';
        NavigableMap<String, Integer> myMap = new TreeMap<String, Integer>();

        for(int i = 0, n = s.length() ; i < n ; i++) {
            char c = s.charAt(i);

            if (previous == c) { // If previous character matches current
                if (myMap.containsKey(c + "")) {
                    myMap.put(c + "", Integer.parseInt(myMap.get(c + "").longValue()+1 + ""));
                }
            } else {
                myMap.put(c + "", Integer.parseInt("1"));
            }
            previous = c;
        }

        String searchString = "";
        for(int j = 0; j < myMap.lastEntry().getValue() ; j++) {
            searchString+=myMap.lastEntry().getKey();
        }

        System.out.println(s.indexOf(searchString));
    }

    @Test
    public void ordersJMS() {
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

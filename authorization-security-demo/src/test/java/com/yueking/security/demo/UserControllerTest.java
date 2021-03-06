package com.yueking.security.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yueking.security.core.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "adm")
                // .param("password","123")
                .param("size", "5")
                .param("page", "0")
                .param("sort", "username,desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:"+result);
    }

    @Test
    public void whenGenInfoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/admin1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("admin1"));
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        User user = new User();
        user.setUsername("yuewu");
        user.setPassword("yueking");
        user.setDel(false);
        user.setCreatedDate(new Date());

        ObjectMapper objectMapper = new JsonMapper();
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        String content = json;
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
        User user1 = objectMapper.readValue(result, User.class);
        System.out.println(objectMapper.writeValueAsString(user1));
    }

    @Test
    public void whenUpdateSuccess()throws Exception{
        User user = new User();
        user.setUsername("yuewu");
        user.setPassword("");
        user.setDel(false);
        user.setCreatedDate(new Date());

        ObjectMapper objectMapper = new JsonMapper();
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        String content = json;
         mockMvc.perform(MockMvcRequestBuilders.put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
                // .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()))
                // .andReturn().getResponse().getContentAsString();
        // System.out.println(result);
        // User user1 = objectMapper.readValue(result, User.class);
        // System.out.println(objectMapper.writeValueAsString(user1));
    }

    @Test
    public void whenDeleteSuccess()throws Exception{
        String userId = "yuewu";
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/"+userId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

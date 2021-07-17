// package com.irme.admin.mvc.controller;


// import com.irme.admin.mvc.config.AppSpringConfig;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// @SpringJUnitWebConfig(AppSpringConfig.class)
// public class WelcomeControllerTest {

//     private MockMvc mockMvc;

//     @Autowired
//     private WebApplicationContext webAppContext;

//     @BeforeEach
//     public void setup() {
//         mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
//     }

//     @Test
//     public void testWelcome() throws Exception {

//         this.mockMvc.perform(
//                 get("/"))
//                 .andDo(print())
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("index"))
//                 .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
//                 .andExpect(model().attribute("msg", "Hello World"));
//     }

// }

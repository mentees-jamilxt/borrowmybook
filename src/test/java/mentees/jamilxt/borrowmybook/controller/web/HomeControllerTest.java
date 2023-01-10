package mentees.jamilxt.borrowmybook.controller.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private MockMvc mockMvc;

    //Check if Application Context starts or not
    @Test
    public void contextLoads() throws Exception {
        assertThat(homeController).isNotNull();
    }

    //Check if expected view is shown or not
    @Test
    public void shouldReturnExpectedView() throws Exception {
        this.mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"))
                    .andDo(print());
    }

}
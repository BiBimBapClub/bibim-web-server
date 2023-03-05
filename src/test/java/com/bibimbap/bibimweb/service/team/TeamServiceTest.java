package com.bibimbap.bibimweb.service.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeamServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    TeamServiceImpl teamService;

    @Test
    @DisplayName("AOP 테스트")
    void aopTest() throws Exception {
        mockMvc.perform(get("/test")).andDo(MockMvcResultHandlers.print());
    }

}

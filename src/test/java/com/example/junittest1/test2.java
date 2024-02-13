package com.example.junittest1;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.example.junittest1.controller.MainController;
import com.example.junittest1.entity.Member;
import com.example.junittest1.service.MainService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class test2 {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private MainService mainService;

        @Autowired
        private ObjectMapper objectMapper;

        private Member member;

        @BeforeEach
        public void initialize() {
                member = Member.builder().id(10).name("aqq").email("aqq@aqq.com").build();
        }

        /*
         * CREATE
         */
        @Test
        void addTest() throws Exception {

                // willanswer확인하기~
                given(mainService.addMember(ArgumentMatchers.any()))
                                .willAnswer((invocation -> invocation.getArgument(0)));

                ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(member)));

                response.andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(jsonPath("$.name").value("aqq"))
                                .andDo(print());
        }

        /*
         * DELETE
         */
        // @Test
        // void deleteTest() throws Exception {
        // String name = "abb";
        // given(mainService.deleteMember("aaaaaa")).willReturn("delete OK");

        // mockMvc.perform(MockMvcRequestBuilders.get("/deletemember"))
        // .andExpect(MockMvcResultMatchers.status().isOk())// andexpectall
        // .andDo(print());
        // }

        /*
         * UPDATE
         */
        // @Test
        // void memberupdate() throws Exception {
        // MultiValueMap<String, String> send = new LinkedMultiValueMap<>();
        // /// send.add("currenteName", "abb");
        // send.add("newName", "qwer");
        // send.add("newEmail", "qwer@qwer.com");

        // given(mainService.memberupdate(ArgumentMatchers.any(),
        // ArgumentMatchers.any(), ArgumentMatchers.any()))
        // .willAnswer((invocation -> invocation.getArguments()));

        // ResultActions response = mockMvc
        // .perform(MockMvcRequestBuilders.patch("/memberupdate/{name}",
        // "abb").params(send));

        // response.andExpect(MockMvcResultMatchers.status().isOk())
        // .andDo(print());

}
// }
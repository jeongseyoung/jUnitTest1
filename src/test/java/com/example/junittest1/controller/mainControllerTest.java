package com.example.junittest1.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import com.example.junittest1.dto.BoardDto;
import com.example.junittest1.dto.MemberDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.MainRepository;
import com.example.junittest1.service.MainService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MainController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class mainControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private MainService mainService;
        @MockBean
        private MainRepository mainRepository;
        @Autowired
        private ObjectMapper objectMapper;
        private Member member;
        private MemberDto memberDto;

        @BeforeEach
        public void init() {
                member = Member.builder().name("aqq").email("aqq@aqq.com").build();
                memberDto = MemberDto.builder().name("bbb").email("bbb@bbb.com").build();
        }

        @Test
        public void addTest_returnMemberDto() throws Exception {
                given(mainService.addMember(ArgumentMatchers.any())).willAnswer(i -> i.getArgument(0));// i =>
                                                                                                       // invocation

                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberDto)));

                resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                                                CoreMatchers.is(memberDto.getName())))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                                                CoreMatchers.is(memberDto.getEmail())))
                                .andDo(MockMvcResultHandlers.print());

        }

        @Test // 배열일때 json -> "$[0].name"
        public void getAllMember_returnMemberDtoList() throws Exception {

                when(mainService.getAllMembers()).thenReturn(Arrays.asList(memberDto));

                // ResultActions resultActions =
                // mockMvc.perform(MockMvcRequestBuilders.get("/getAllMembers")
                // .contentType(MediaType.APPLICATION_JSON));
                // .content(objectMapper.writeValueAsString(memberDto)));

                ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/getAllMembers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberDto)));

                response.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",
                                                CoreMatchers.is(memberDto.getName())))
                                .andDo(print());
        }

        @Test // delete
        public void deleteMember() throws Exception {
                // given(mainService.addMember(memberDto)).willAnswer(invocation ->
                // invocation.getArgument(0));
                assertAll(() -> mainService.deleteMember(0));
        }

        @Test
        public void updateMember() throws Exception {
                when(mainService.memberupdate(Mockito.anyInt(), Mockito.any(MemberDto.class))).thenReturn(memberDto);

                ResultActions resutlActions = mockMvc.perform(MockMvcRequestBuilders.patch("/memberupdate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberDto)));

                resutlActions.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(jsonPath("$.name", CoreMatchers.is(memberDto.getName())))
                                .andDo(print());

        }
        /*
         * given
         * 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
         *
         * when
         * 구체화하고자 하는 그 행동
         *
         * then
         * 어떤 특정한 행동 때문에 발생할거라고 예상되는 변화에 대한 설명
         */
}

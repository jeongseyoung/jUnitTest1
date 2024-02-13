package com.example.junittest1;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.web.JsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.junittest1.controller.MainController;
import com.example.junittest1.service.MainService;

@WebMvcTest(MainController.class)
class Junittest1ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MainService mainService;

	@Test
	@DisplayName("getMember Test")
	void getMemberTest() throws Exception {
		/*
		 * mainService라는 모의 객체에서 getMemberName("abb") 메소드가 호출될 때 "John"을 반환하도록 설정
		 */
		given(mainService.getMemberName("abb")).willReturn("abb");

		mockMvc.perform(MockMvcRequestBuilders.get("/getMemberName")).andExpect(MockMvcResultMatchers.status().isOk());
		// .andExpect(r -> r.getResponse());

		/*
		 * verify는 모의 객체의 메소드가 실제로 호출되었는지를 검증하는 데 사용
		 */
		mainService.getMemberName("abb");
		mainService.getMemberName("abb");
		verify(mainService, times(2)).getMemberName("abb");

		/*
		 * given은 모의 객체의 동작을 설정하고, verify는 특정 메소드 호출을 검증합니다.
		 * 이들은 보통 같이 사용되어 특정 상황에서 모의 객체가 예상한 대로 동작하는지 확인하는 데 활용됩니다
		 */
	}

	// @Test
	// @DisplayName("addmember()")
	// void addMember() throws Exception {
	// Member member = Member.builder().name("B").email("b@bb.com").build();// new
	// Member(2, "B", "b@bb.com")
	// // Member member2 = mainService.getMember("B");
	// // given(mainService.addMember("B", "b@bb.com").willReturn(member2));

	// Gson gson = new Gson();
	// String jsonmember2 = gson.toJson(member);
	// // String json = new ObjectMapper().writeValueAsString(member2);

	// mockMvc.perform(
	// MockMvcRequestBuilders.post("/add").content(jsonmember2).contentType(MediaType.APPLICATION_JSON))
	// .andExpect(MockMvcResultMatchers.status().isOk())
	// .andExpect(jsonPath("$.name").value("B"));

	// }

}

package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

public class LoginControllerTest extends WebTestConfig{
	
	
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/login/view"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/view"));
	}
	
	// 로그인 요청 테스트(정상적인 경우)
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
				.param("pass", "brownPass")).
		andExpect(status().is(200))
		.andExpect(view().name("main"));
	}
	
	// 로그인 요청 테스트 (실패)
	@Test
	public void processFailTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/login/process")
				.param("userid", "brown")
				.param("pass", "brownPassFail")).andReturn();
		// andExpect() 들을 MvcResult로 return 받아서 처리할 수도 있다.
		
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		assertEquals("login/view", viewName);
		assertEquals("fail", mav.getModel().get("msg"));
	}
}

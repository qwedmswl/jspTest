package kr.or.ddit.fileupload.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;
public class FileUploadControllerTest extends WebTestConfig {

	
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/fileupload/view"))
			.andExpect(status().is(200))
			.andExpect(view().name("fileupload/fileupload")).andDo(print());
	}
	
	@Test
	public void uploadTest() throws Exception {
		
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
//		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload");
		
		MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", is);
		
		mockMvc.perform(fileUpload("/fileupload/upload")
									.file(file)
									.param("userid", "브라운"))
						.andExpect(view().name("fileupload/fileupload"))
						.andExpect(status().isOk());
	}
}

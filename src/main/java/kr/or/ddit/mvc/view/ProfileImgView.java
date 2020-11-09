package kr.or.ddit.mvc.view;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ProfileImgView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FileInputStream fis = new FileInputStream((String)model.get("filepath"));
//		FileInputStream fis = new FileInputStream("D:\\profile\\cony.png");
		
		
		// 응답 생성
		ServletOutputStream sos = response.getOutputStream();

		byte[] buffer = new byte[512];
		
		while (fis.read(buffer) != -1) {
			sos.write(buffer);
		}

		fis.close();
		sos.flush();
		sos.close();
		
	}


}

package kr.or.ddit.mvc.view;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + (String)model.get("filepath") + "\"");
		response.setContentType("application/octet-stream");
		
		FileInputStream fis = new FileInputStream((String)model.get("filepath"));
		
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


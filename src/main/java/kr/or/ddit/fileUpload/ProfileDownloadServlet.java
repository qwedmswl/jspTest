package kr.or.ddit.fileUpload;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/profileDownload")
public class ProfileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProfileDownloadServlet.class);
	private MemberServiceI memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 사용자 아이디 파라미터 확인하고
		String userid = request.getParameter("userid");

		// db에서 사용자 filename 확인
		MemberVo memberVo = memberService.getMember(userid);

		// response content-type 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVo.getRealFilename() + "\"");
		response.setContentType("application/octet-stream");
		
		// 경로 확인후 파일 입출력을 통해 응답 생성
		// 파일을 읽고
//		memberVo.getFilename(); // 파일 경로
		
		FileInputStream fis = new FileInputStream(memberVo.getFilename());
		
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

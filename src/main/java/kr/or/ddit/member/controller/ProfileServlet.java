package kr.or.ddit.member.controller;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/profileImg")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceI memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// response content-type 설정
		response.setContentType("image/png");
		
		// 사용자 아이디 파라미터 확인하고
		String userid = request.getParameter("userid");

		// db에서 사용자 filename 확인
		MemberVo memberVo = memberService.getMember(userid);

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

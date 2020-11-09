package kr.or.ddit.member.web;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@Controller
public class ProfileController {
	
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@RequestMapping("/profileImgView")
	public String profileImgView(String userid, Model model) throws IOException{
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 output stream 객체에 쓰는 것
		
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath", memberVo.getFilename());
		
		return "profileImgView";
	}
	
	@RequestMapping("/profileImgDown")
	public String profileImgDown(String userid, Model model) throws IOException{
		
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath", memberVo.getFilename());
		
		return "profileImgDown";
	}
//	@RequestMapping("/profileImg")
//	public void profileImg(String userid, HttpServletResponse response) throws IOException {
//
//		// response content-type 설정
//		response.setContentType("image/png");
//		
//		// db에서 사용자 filename 확인
//		MemberVo memberVo = memberService.getMember(userid);
//
//		// 경로 확인후 파일 입출력을 통해 응답 생성
//		// 파일을 읽고
////		memberVo.getFilename(); // 파일 경로
//		
//		FileInputStream fis = new FileInputStream(memberVo.getFilename());
////		FileInputStream fis = new FileInputStream("D:\\profile\\cony.png");
//		
//		
//		// 응답 생성
//		ServletOutputStream sos = response.getOutputStream();
//
//		byte[] buffer = new byte[512];
//		
//		while (fis.read(buffer) != -1) {
//			sos.write(buffer);
//		}
//
//		fis.close();
//		sos.flush();
//		sos.close();
//	}
}

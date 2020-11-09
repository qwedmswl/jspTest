package kr.or.ddit.member.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
					   @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
					   Model model) {
				
		//pageVo : page, pageSize
		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
				
		return "member/list";
	}
	
	@RequestMapping("/member")
	public String member(String userid, Model model) {
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "member/member";
	}
	
	@RequestMapping(path="/regist", method = {RequestMethod.GET})
	public String regist() {
		return "member/regist";
	}

	@RequestMapping(path="/regist", method = {RequestMethod.POST})
	public String regist(@Valid MemberVo memberVo, BindingResult br, MultipartFile profile) {
	//public String regist(@Valid JSRMemberVo memberVo, BindingResult br, MultipartFile profile) {

		//new MemberVoValidator().validate(memberVo, br);

		//검증을 통과하지 못했으므로 사용자 등록 화면으로 이동
		if(br.hasErrors()) {
			return "member/regist";
		}

		String realFilename = profile.getOriginalFilename();
		String extension =  FileUploadUtil.getExtension(realFilename);
		String fileName = UUID.randomUUID().toString();
		String filePath = "";
		if(profile.getSize() > 0) {
			filePath = "D:\\profile\\" + fileName + "." + extension;
			File file = new File(filePath);
			try {
				profile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		//사용자 정보 등록
		memberVo.setFilename(filePath);
		memberVo.setRealFilename(realFilename);
		
		int insertCnt = memberService.insertMember(memberVo);
		
		//1건이 입력되었을 때 : 정상 - memberList 페이지로 이동
		if(insertCnt == 1) {
			return "redirect:/member/list";
		}
		//1건이 아닐때 : 비정상 - 사용자가 데이터를 다시 입력할 수 있도록 등록페이지로 이동
		else {
			return "member/regist";
		}
	}
	
	@RequestMapping(path="/update", method= {RequestMethod.GET})
	public String update(String userid, Model model) {
		model.addAttribute("memberVo", memberService.getMember(userid));
		return "member/update";
	}
	
	@RequestMapping(path="/update", method= {RequestMethod.POST})
	public String update(MemberVo memberVo, Model model, MultipartFile profile) {
		
		String realFilename = "";
		String filePath = "";
		if(profile != null) {
			
			
			realFilename = profile.getOriginalFilename();
			String extension = FileUploadUtil.getExtension(realFilename);
			String fileName = UUID.randomUUID().toString();
			
			if(profile.getSize() > 0) {
				filePath = "D:\\profile\\" + fileName + "." + extension;
				File file = new File(filePath);
				try {
					profile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				memberVo.setFilename(filePath);
				memberVo.setRealFilename(realFilename);
			}
		}
		
		int updateCnt = memberService.updateMember(memberVo);
		
		//1건이 입력되었을 때 : 정상 - memberList 페이지로 이동
		if(updateCnt == 1) {
			return "redirect:/member/member?userid=" + memberVo.getUserid();
		}
		//1건이 아닐때 : 비정상 - 사용자가 데이터를 다시 입력할 수 있도록 등록페이지로 이동
		else {
			return "member/update";
		}
	}
}
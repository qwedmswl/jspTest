package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private MemberServiceI service;
	
	@RequestMapping(path="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(name="page", required=false, defaultValue="1")int page,
									@RequestParam(name="pageSize", required=false, defaultValue="5")int pageSize, Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> map = service.selectMemberPageList(pageVo);
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "member/list";
		
	}
	
	@RequestMapping("/member")
	public String member(String userid, Model model) {
		MemberVo memberVo = service.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "member/member";
	}
	
	@RequestMapping(path="/regist", method = {RequestMethod.POST})
	public String regist(MemberVo memberVo, MultipartFile profile) {
		
		String realFilename = profile.getOriginalFilename();
		String extension = FileUploadUtil.getExtension(realFilename);
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
		
		memberVo.setFilename(filePath);
		memberVo.setRealFilename(realFilename);
		
		int insertCnt = service.insertMember(memberVo);
		
		if(insertCnt == 1) {
			return "redirect:/memeber/list";
		}
		else {
			return "member/regist";
		}
	}
	
	@RequestMapping(path="/update", method= {RequestMethod.GET})
	public String update(String userid, Model model) {
		model.addAttribute("memberVo", service.getMember(userid));
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
				File file = new File(fileName);
				try {
					profile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				memberVo.setFilename(fileName);
				memberVo.setRealFilename(realFilename);
			}
		}
		int updateCnt = service.updateMember(memberVo);
		
		if(updateCnt == 1) {
			return "redirect:/member/member?userid=" + memberVo.getUserid();
		}
		else {
			return "member.update";
		}
	}
}

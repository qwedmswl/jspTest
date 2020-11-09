package kr.or.ddit.login.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

//@WebServlet 혹은 web.xml url-mapping을 통해 url 등록

@SessionAttributes("rangers")
@RequestMapping("/login")
@Controller
public class LoginController {
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ModelAttribute("rangers")
	public List<String> ranger(){
		logger.debug("ranger()");
		List<String> rangers = new ArrayList<String>();
		
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		
		// rangers라는 속성으로 화면에 띄우면 배열로 오게됨
		return rangers;
	}
	
	// localhost/login/json
	// ranger() ==> Model 객체에 rangers 라는 이름의 속성이 저장됨 ==> json()
	// Model객체 속성이 존재(rangers)
	
	@RequestMapping("/json")
	public String json() {
		
		return "jsonView";		//application에 저장해놓은 <bean id="jsonView" class="MappingJack2JsonView"
		
				// view resolver를 두개 등록함
				// 1. beanNameViewResolver
				// 		viewName해당하는 빈이 있는지 찾음
				//		만약 해당하는 빈(View)이 있으면 해당 view 결과를 생성
				// 		beanNameViewResolver에서 찾지 못했을 경우
				// 2. internalResourceViewResolver
				//		prefix, surfix 설정에 따라 /WEB-INF/views/jsonView.jsp
				//		internalResourceViewResolver는 view이름에 해당하는
				// 		자원이 존재하는지, 존재하지 않는지 체크하지 않고 무조건 forwarding
				// 		** viewResolver 우선순위를 가장 후순위로 미뤄야함
	}
	
	//localhost/login/view 요청시 처리되는 메소드
	// 요청 메소드가 GET일 때만 처리
//	@GetMapping
	@RequestMapping(path="/view", method = RequestMethod.GET)
	public String getView() {
		logger.debug("LoginController.getView()");
		return "login/view";
	}
	
	// 파라미터 이름과 동일한 이름의 메소드 인자를 선언하면
	// 스프링 프레임워크가 자동으로 바인딩 해준다
	// 값을 담을 수 있는 객체를 메소드 인자로 선언한 경우에도 필드명과 파라미터 명이
	// 동일하면 자동으로 바인딩 처리를 해준다
	// 이때 값을 담는 객체를 스프링 프레임워크에서는 command 객체라고 명명한다
	
	// Model : view 객체에서 응답을 생성할 때 참조할 데이터를 담는 객체
	//			jsp/servlet 기반의 request 역할을 담당
	
	@RequestMapping(path="/process", params = {"userid"} )
	public String process(String userid, String pass, MemberVo memberVo, HttpSession session, Model model
								, @RequestParam(name="email", required = false, defaultValue = "brown@line.kr") String user_id) {
		
		logger.debug("LoginController.process() {} / {} / {}", userid, pass, memberVo);
		logger.debug("user_id : {}", user_id);
		
		MemberVo dbMember = memberService.getMember(userid);
		logger.debug("dbMember : {}", dbMember);
		
		if(dbMember != null && memberVo.getPass().equals(dbMember.getPass())) {
			session.setAttribute("S_MEMBER", dbMember);
			
			// jsp/servlet 기반에서 사용한 코드 : request.setAttribute("to_day", new Date());
			model.addAttribute("to_day", new Date());
			
//			prefix : /WEB-INF/views/
//			suffix : .jsp
			return "main";
			
		}
		else {
			model.addAttribute("msg", "fail");
			return "login/view";
		}
	}
	
	// localhost/login/unt/dd
	
	@RequestMapping("/unt/{unt_cd}")
	public String untMain(@PathVariable("unt_cd") String unt_cd) {
		logger.debug("unt_cd : {}", unt_cd);
		return "main";
	}
	
	// localhost/login/mavView
	@RequestMapping("/mavView")
	public ModelAndView mavView(@ModelAttribute("rangers") List<String> rangers,
								@ModelAttribute("test") MemberVo memberVo) {
		ModelAndView mav = new ModelAndView();
		
		logger.debug("mavView rangers : {}", rangers);
		// view name 설정
		mav.setViewName("main");
		
		mav.getModel().put("msg", "success");
		mav.getModelMap().addAttribute("msg", "fail");
		
		return mav;
	}
}

package kr.or.ddit.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

@Service("memberService")
public class MemberService implements MemberServiceI {

	@Resource(name="memberDao")
	private MemberDaoI dao;
	
	public MemberService() {
//		dao = new MemberDao();
		// 이건 필드에다 직접 설정하는 형태여서 bean을 사용한게아님
	}
	
	@Override
	public MemberVo getMember(String userid) {
		
		return dao.getMember(userid);
	}

}

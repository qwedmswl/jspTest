package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
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

	@Override
	public List<MemberVo> selectAllMember() {
		return dao.selectAllMember();
	}

	@Override
	public Map<String, Object> selectMemberPageList(PageVo pageVo) {

		SqlSession sqlSession = MybatisUtil.getSqlSession();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberList", dao.selectMemberPageList(sqlSession, pageVo));

		// 15건, 페이지 사이즈를 7로 가정했을때 3개의 페이지가 나와야한다
		// 15/7 = 2.14.... 올림을 하여 3개의 페이지가 필요
		// Math.ceil()
		int totalCnt = dao.selectMemberTotalCnt(sqlSession);
		int pages = (int) Math.ceil((double) totalCnt / 7);
		map.put("pages", pages);

		sqlSession.close();
		return map;
	}


	@Override
	public int insertMember(MemberVo memberVo) {
		
		return dao.insertMember(memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		
		return dao.deleteMember(userid);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return dao.updateMember(memberVo);
	}

}

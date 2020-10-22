package kr.or.ddit.member.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

	MemberServiceI memberService;
	@Test
	public void getMemberTest() {
		/***Given***/
		memberService = new MemberService();
		String userId = "brown";
		
		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");
		/***When***/
		MemberVo memberVo = memberService.getMember(userId);

		/***Then***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());
		assertEquals("brown", memberVo.getUserid());
		assertEquals("brownPass", memberVo.getPass());

//		assertEquals(answerMemberVo, memberVo);
		//assertEquals(answerMemberVo, memberVo);
	}

	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		memberService = new MemberService();
		PageVo pageVo = new PageVo(1, 7);

		/***When***/
		//memberList 확인
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		List<MemberVo> memberList = (List<MemberVo>)map.get("memberList");

		//생성해야할 page 수
		int pages = (int)map.get("pages");

		/***Then***/
		assertEquals(7, memberList.size());
		assertEquals(3, pages);
	}
	@Test
	public void localeListTest() {
		Locale[] locales = SimpleDateFormat.getAvailableLocales();
		for(Locale locale : locales) {
			logger.debug("{}",locale);
		}
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		memberService = new MemberService();
		memberService.deleteMember("brown");
		MemberVo memberVo = new MemberVo("ldy", "pass1234", "이은지", "DY", 
											"대전 중구 중앙로 76", "영민빌밍 404호", "34940", 
											"d:\\profile\\lej.png", "lej.png");

		/***When***/
		int insertCnt = memberService.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteMemberTest() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("member.deleteMember");
		}
}
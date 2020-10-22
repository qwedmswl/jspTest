package kr.or.ddit.member.dao;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

public class MemberDaoTest {
	
	/* 테스트 메소드 실행 사이클: 
	 @BeforeClass (static)
		@Before => @Test => @After
		@Before => @Test => @After
		@Before => @Test => @After
	 @AfterClass (static)
	 */
	MemberDaoI memberDao;
	
	@Before // 인자값 없어야함
	
	public void setup() {
		memberDao = new MemberDao();
		String userid = "lej";
		
		memberDao.deleteMember(userid);

	}
	@Test
	public void getMemberTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		String userId = "brown";
		
		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");
		/***When***/
		MemberVo memberVo = memberDao.getMember(userId);

		/***Then***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());
		assertEquals("brown", memberVo.getUserid());
		assertEquals("brownPass", memberVo.getPass());

//		assertEquals(answerMemberVo, memberVo);
		//assertEquals(answerMemberVo, memberVo);
	}

	@Test
	public void selectAllMemberTest() {
		/***Given***/
		memberDao = new MemberDao();
		
		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();

		/***Then***/
//		assertEquals(7, );
		assertEquals(15, memberList.size());
		//assertEquals("brown", memberList.get(0).getUserid());
	}

	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		memberDao = new MemberDao();
		PageVo pageVo = new PageVo(1, 7);
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		//int page = 1;

		/***When***/
		List<MemberVo> memberList = memberDao.selectMemberPageList(sqlSession, pageVo);

		/***Then***/
		assertEquals(7, memberList.size());
	}

	@Test
	public void selectMemberTotalCntTest() {
		/***Given***/
		memberDao = new MemberDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		/***When***/
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);

		/***Then***/
		assertEquals(15, totalCnt);
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		memberDao = new MemberDao();
		memberDao.deleteMember("brown");
		MemberVo memberVo = new MemberVo("ldy", "pass1234", "이은지", "DY", 
											"대전 중구 중앙로 76", "영민빌밍 404호", "34940", 
											"d:\\profile\\lej.png", "lej.png");

		/***When***/
		int insertCnt = memberDao.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteMemberTest() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("member.deleteMember");
	}
}
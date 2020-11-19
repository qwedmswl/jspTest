package kr.or.ddit.member.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.config.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;



public class MemberDaoTest extends ModelTestConfig {

	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		

		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();
		
		/***Then***/
		assertTrue(memberList.size() > 13 );
	}
	
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 7);
		SqlSession session = MybatisUtil.getSqlSession();
		
		/***When***/
		List<MemberVo> memberVo = memberDao.selectMemberPageList(session, pageVo);
		
		/***Then***/
		assertEquals(7, memberVo.size());
		
	}
	
	@Test
	public void insertMember() {
		/***Given***/
		MemberVo memberVo = new MemberVo("ljm", "jmPass", "이재명", "재명뚱돼지", "", "", "", "", "");

		/***When***/
		int insertMem = memberDao.insertMember(memberVo);

		/***Then***/
		assertEquals(insertMem, 1);
	}

	@Test
	public void updateMember() {
		/***Given***/
		MemberVo memberVo = new MemberVo("ljm", "jmPass", "이재명", "재명뚱돼지", "", "", "", "", "");
		
		int insertMem = memberDao.insertMember(memberVo);
		
		/***When***/
		memberVo.setAlias("뚱보재명");
		int updateMem = memberDao.updateMember(memberVo);
		
		/***Then***/
		assertEquals(insertMem, 1);
		assertEquals(updateMem, 1);
		
	}
	
	@Test
	public void selectMemberTotalCnt() {
		/***Given***/
		SqlSession session = MybatisUtil.getSqlSession();

		/***When***/
		int total = memberDao.selectMemberTotalCnt(session);

		/***Then***/
		assertEquals(total, 17);
	}
}

package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest extends ModelTestConfig {
	
	@Resource(name="memberService")
	private MemberServiceI service;
	
	@Test
	public void insertMember_SUCCESS_Test() {
		
		/***Given***/
		MemberVo memberVo = new MemberVo("jm", "jmPass", "짜밍", "재뚱", "", "", "", "", "");
		
		/***When***/
		int insertCnt = service.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}

	
//	@Test
	public void insertMember_FAIL_Test() {
		
		/***Given***/
		MemberVo memberVo = new MemberVo("jm", "jmPass", "짜밍", "재뚱", "", "", "", "", "");
		
		/***When***/
		int insertCnt = service.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
}

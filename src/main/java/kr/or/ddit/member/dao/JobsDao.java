package kr.or.ddit.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.JobsVo;

public class JobsDao implements JobsDaoI {

	@Override
	public JobsVo getJobs(String jobId) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		JobsVo jobsVo = sqlSession.selectOne("jobs.getJobs", jobId);
		
		return jobsVo;
	}

	
}

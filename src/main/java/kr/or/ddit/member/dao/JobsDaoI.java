package kr.or.ddit.member.dao;

import kr.or.ddit.member.model.JobsVo;

public interface JobsDaoI {
	
	JobsVo getJobs(String jobId);
}

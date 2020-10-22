package kr.or.ddit.jobs.dao;

import org.junit.Test;

import kr.or.ddit.member.dao.JobsDao;
import kr.or.ddit.member.model.JobsVo;

public class JobsDaoTest {

	@Test
	public void test() {
		/***Given***/
		JobsDao jobsDao = new JobsDao();
		String jobId = "sally";
		
		JobsVo answerJobsVo = new JobsVo();
		answerJobsVo.setJobId("sally");
		answerJobsVo.setJobTitle("");

		/***When***/

		/***Then***/
	}

}

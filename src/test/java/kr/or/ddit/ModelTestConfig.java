package kr.or.ddit;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//repository + service : root-context.xml
//sqlSessionTemplate : datasource-context.xml
//transaction : transaction-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml",
									"classpath:kr/or/ddit/config/spring/datasource-context_dev.xml",
									"classpath:kr/or/ddit/config/spring/transaction-context.xml"})

public class ModelTestConfig {
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Ignore
	@Test
	public void dummy() {
		
	}
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		new ClassPathResource("/kr/or/ddit/config/db/initData.sql"); 스크립트에 대한 경로 설정 addScript의 인자로 넘겨줄 수 있음
		populator.addScripts(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		
		populator.setContinueOnError(false);
		
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
}

package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board"})
public class JavaSpringScanConfig {
	// Test코드를 실행할 때 kr.or.ddit 전체를 읽어오기때문에 오류가 뜸 그래서 kr.or.ddit.board로 지정하여 board 패키지만 읽을 수 있도록 설정 
}

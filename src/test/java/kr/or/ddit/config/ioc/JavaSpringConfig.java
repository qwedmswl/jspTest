package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class JavaSpringConfig {

	// boardRepository, boardService
	// 특별한 설정을 할 수도 있기도 함
	// 메소드이름 ==> 스프링 빈 이름

	// xml : <bean id="boardRepository(메소드이름)" class="BoardRepository"/>
	@Bean
	public BoardRepositoryI boardRepository() {
		
		BoardRepositoryI boardRepository = new BoardRepository();
		
		return boardRepository;
//		return new BoardRepository(); 간단하게 사용 가능
	}
	
	// xml : <bean id="boardService(메소드이름)" class="BoardService"/>
	@Bean
	public BoardService boardService() {
		
		BoardService boardService = new BoardService();
		boardService.setBoardRepository(boardRepository());
		
		// 아래와 같이 직접 new 연산자를 통해 생성하는 객체는 스프링 빈이 아니다
		// @Bean 어노테이션이 붙은 메소드를 호출해야 스프링 컨테이너에서 관리되는 스프링빈을
		// 얻을 수 있다.
//		boardService.setBoardRepository(new BoardRepository());
		
		return boardService;
//		return new BoardService(); 간단하게 사용 가능
		
	}
}

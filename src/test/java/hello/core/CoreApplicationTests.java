package hello.core;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 스프링이 자동으로 스프링 컨테이너를 띄워서, 이 테스트를 통합해서 실행해줌.
@SpringBootTest
class CoreApplicationTests {

	// 테스트에서는 Autowired 사용해도 괜찮음.
//	@Autowired
//	MemberService memberService;

	@Test
	void contextLoads() {
	}

}

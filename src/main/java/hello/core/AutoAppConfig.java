package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component가 붙은 클래스들을 자동으로 스프링 빈으로 등록해준다.
@ComponentScan(
        // @Configuration도 @Component를 가지고 있기 때문에.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없다.
public class AutoAppConfig {

/*
    // 충돌을 위해 수동으로 빈을 등록한다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/

}

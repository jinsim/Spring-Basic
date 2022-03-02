package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // 출력
        // System.out.println("memberService = " + memberService);
        // 어떤 타입인지 확인
        // System.out.println("memberService.getClass() = " + memberService.getClass());

        // 우리가 등록했던 MemberServiceImpl로 잘 나오는지 확인
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanType() {
        // 이름이 없어지니 더 편리함. 그러나, 장단점이 있음. 같은 타입이 여러개일 경우는..?
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 위에서는 인터페이스로 조회했음. 그럼 인터페이스의 구현체가 대상이 된다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // AppConfig에서 memberService()의 반환 타입이 memberService이지만, 스프링 빈에 등록된 인스턴스 타입으로 파악할 수도 있다.
        // 별로 좋은 코드는 아님. 역할과 구현은 구분해야하고, 역할에 의존해야한다.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패했을 경우 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // ac.getBean("xxxxx", MemberService.class);
        // 람다식이 실행됐을 때, 첫번째 파라미터에 있는 예외가 터져야한다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}

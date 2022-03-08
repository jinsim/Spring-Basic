package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        // 아래에서 만든 TestBean이 스프링 빈으로 등록이 된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // Member는 스프링이 관리하는 빈이 아니다. 즉, 아래의 코드에서 스프링 컨테이너가 관리하는 것은 없다.
        // 그래서 required를 true로 하거나 없애면, 에러가 발생한다.
        // Member가 스프링 빈으로 등록되어있지 않으므로 찾을 수 없다.
        @Autowired(required = false)    // 메소드 자체가 호출 안됨. 출력 결과 안나옴.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired  // null 출력 
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired  // Optional.empty 출력
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

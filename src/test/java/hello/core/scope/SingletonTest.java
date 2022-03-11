package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 싱글톤 스코프 빈 테스트
public class SingletonTest {

    @Test
    void singletonBeanFind() {
        // AnnotationConfigApplicationContext의 파라미터는, 컴포넌트 클래스를 넣어주는 것.
        // 해당 클래스에 @Component가 없어도, 컴포넌트 스캔의 대상이 된 것처럼 등록된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        // isSameAs는 == 비교.
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    // 싱글톤 빈이 있는 설정 정보
    @Scope("singleton") // 디폴트라 안적어도 됨. 공부용
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}

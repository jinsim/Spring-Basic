package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// JUnit5 부터는 클래스명과 메소드명에 public 안붙여도 됨
class ApplicationContextInfoTest {
    // AnnotationConfigApplicationContext를 ac로 줄여 사용함.
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 정의된 빈의 이름들을 꺼낼 수 있다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 타입 지정을 안했으므로 Object로 받아진다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 하나하나에 대한 메타 데이터들.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 스프링이 내부에서 등록한 빈이 아니라, 내가 애플리케이션을 개발하거나 혹은 외부 라이브러리에서 등록한 빈.
            // BeanDefinition.ROLE_INFRASTRUCTURE은, 스프링 내부에서 사용하는 빈.
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}

package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ConfigurableApplicationContext는 AnnotationConfigApplicationContext 보다 상위,
        // ApplicationContext 보다 하위에 있음.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        // ApplicationContext를 닫아야하는데, 이는 기본 ApplicationContext 인터페이스 에서 제공해주지 않음. (할 일이 별로 없기 때문에)
        // AnnotationConfigApplicationContext로 반환 타입을 바꾸거나, ConfigurableApplicationContext(둘 사이)로 변경하면 된다.
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // 스프링 빈이 생성이 되고, 호출된 결과물이 스프링 빈으로 등록이 될 것임.
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

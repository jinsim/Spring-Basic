package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
// @Scope("request") // 파라미터가 하나면 value를 생략해주어도 된다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // MyLogger의 프록시를 만든다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    // 중간에 받도록 설정
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    // 로그를 남기는 메소드
    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    // 고객 요청이 들어와서 최초로 스프링에 달라고 요청하면 호출된다.
    @PostConstruct
    public void init() {
        // 절대 겹치지 않는 uuid가 생성된다.
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope been create:" + this);
    }

    // request 스코프는 PreDestroy 가능. 요청이 빠져나갈 때 호출된다.
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope been close:" + this);
    }
}

package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        // 기존에는 MemberService에 대한 객체와, 그 객체에서 어떤 객체들이 사용될 건지를 다 실행 코드 안에서 작성했음.
//        MemberService memberService = new MemberServiceImpl();

/*

        // 이제는 AppConfig에서 객체를 생성해주면서, DI를 해준다.
        AppConfig appConfig = new AppConfig();
        // memberService에는 memberServiceImpl이 들어있다.
        // memoryMemberRepository도 주입되어있는 상태이다.
        MemberService memberService = appConfig.memberService();
*/

        // ApplicationContext가 스프링 컨테이너다.
        // AppConfig.class에 있는 정보들을 가지고 컨테이너 내에 객체를 생성하고 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 첫번째 파라미터는 AppConfig.class 내의 메소드 명이다.
        // 두번째 파라미터는 반환 타입이다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

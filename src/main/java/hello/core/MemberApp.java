package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        // 기존에는 MemberService에 대한 객체와, 그 객체에서 어떤 객체들이 사용될 건지를 다 실행 코드 안에서 작성했음.
//        MemberService memberService = new MemberServiceImpl();

        // 이제는 AppConfig에서 객체를 생성해주면서, DI를 해준다.
        AppConfig appConfig = new AppConfig();
        // memberService에는 memberServiceImpl이 들어있다.
        // memoryMemberRepository도 주입되어있는 상태이다.
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

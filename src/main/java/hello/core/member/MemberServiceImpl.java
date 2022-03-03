package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 캄포넌트 등록을 위해
@Component
public class MemberServiceImpl implements MemberService{

    // 인터페이스뿐만 아니라 구현체까지 의존하고 있음. DIP 위반
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // @Component를 사용하면 자동으로 스프링 빈에 등록이 되므로, 의존관계를 주입하기 위해 Autowired를 사용해야한다.
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤이 깨지는 지를 확인하기 위한 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

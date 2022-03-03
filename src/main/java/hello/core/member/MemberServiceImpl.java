package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 인터페이스뿐만 아니라 구현체까지 의존하고 있음. DIP 위반
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

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

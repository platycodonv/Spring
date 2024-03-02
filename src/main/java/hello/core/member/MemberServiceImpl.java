package hello.core.member;

public class MemberServiceImpl implements MemberService{


    /*interface 만 존재. -> 추상화에만 의존 (DIP)*/
    private MemberRepository memberRepository;

    /*생성자를 통해 주입받는다.*/
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
}

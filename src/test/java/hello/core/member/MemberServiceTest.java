package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {

        //given
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertEquals(memberA, findMember);

    }
}

package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{



    /*
    1. 할인 금액 정책의 변경이 -> 할인 정책의 클라이언트인 OderServiceImpl의 코드가 변경이 일어난다.(OCP 위반)
    인터페이스(추상) 뿐 아니라 구현 클래스에 의존, (DIP 위반)
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDisCountPolicy();
    */

    /*
    2. 추상화인 interface에만 의존하도록 변경 -> NPE
    구현체가 없다 !
    해결방안 : 누군가 클라이언트인 DiscountPolicy의 구현 객체를 대신 생성하고 "주입" 해주어야 한다.
    private DiscountPolicy discountPolicy;
    */

    /*3. 생성자 주입 */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* #2 -> #3 생성자 파라미터 오기입 수정 */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //OrderService 입장에서는 할인에대한건 알지 못하지만 결과만 받을 수 있다.
        //단일책임원칙이 잘 지켜졌다. 할인에 대한 변경은 신경 쓸 필요가 없다.
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}

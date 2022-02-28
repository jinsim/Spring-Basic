package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액.
     * 이걸 호출하고 나면, 결과로 할인된 금액을 반환해줌
     */
    int discount(Member member, int price);
}

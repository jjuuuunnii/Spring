package practice.prac.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.prac.discount.DiscountPolicy;
import practice.prac.member.Member;
import practice.prac.member.MemberService;

@Component
public class OrderServiceImpl implements OrderService {

    private DiscountPolicy discountPolicy;
    private MemberService memberService;

    @Autowired
    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberService memberService) {
        this.discountPolicy = discountPolicy;
        this.memberService = memberService;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findById(memberId);
        int discount = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discount);
    }
}

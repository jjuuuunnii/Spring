package practice.prac.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import practice.prac.member.Grade;
import practice.prac.member.Member;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private int rateDiscount = 10;
    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP) {
            return itemPrice * rateDiscount / 100;
        }else{
            return 0;
        }
    }
}

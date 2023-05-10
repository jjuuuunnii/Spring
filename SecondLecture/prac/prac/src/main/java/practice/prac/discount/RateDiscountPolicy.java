package practice.prac.discount;

import practice.prac.member.Grade;
import practice.prac.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10; //할인율

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        }else{
            return 0;
        }
    }
}

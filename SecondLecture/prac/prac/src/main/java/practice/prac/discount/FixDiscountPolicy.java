package practice.prac.discount;

import practice.prac.member.Grade;
import practice.prac.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private final int fixDiscount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return 1000;
        }else{
            return 0;
        }
    }
}

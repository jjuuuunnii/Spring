package practice.prac.discount;

import org.springframework.stereotype.Component;
import practice.prac.member.Grade;
import practice.prac.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy{
    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP){
            return 1000;
        }else{
            return 0;
        }
    }
}

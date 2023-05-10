package practice.prac.discount;

import practice.prac.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}

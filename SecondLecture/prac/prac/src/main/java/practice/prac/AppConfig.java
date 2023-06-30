package practice.prac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.prac.discount.DiscountPolicy;
import practice.prac.discount.FixDiscountPolicy;
import practice.prac.discount.RateDiscountPolicy;
import practice.prac.member.MemberRepository;
import practice.prac.member.MemberService;
import practice.prac.member.MemberServiceImpl;
import practice.prac.member.MemoryMemberRepository;
import practice.prac.order.OrderService;
import practice.prac.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(discountPolicy(),memberService());
    }
}

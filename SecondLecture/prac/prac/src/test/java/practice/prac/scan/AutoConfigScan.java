package practice.prac.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.prac.AutoAppConfig;
import practice.prac.discount.DiscountPolicy;
import practice.prac.discount.RateDiscountPolicy;
import practice.prac.member.MemberRepository;
import practice.prac.member.MemberService;
import practice.prac.member.MemoryMemberRepository;
import practice.prac.order.OrderService;
import practice.prac.order.OrderServiceImpl;

import java.util.List;
import java.util.Map;

public class AutoConfigScan {

    ApplicationContext ac;

    @BeforeEach
    void beforeEach(){
        ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    }

    @Test
    void scanAllConfig(){
        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderServiceImpl.class);
        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

}

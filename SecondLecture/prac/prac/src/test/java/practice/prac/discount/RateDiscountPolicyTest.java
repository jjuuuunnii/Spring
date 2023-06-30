package practice.prac.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.prac.AppConfig;
import practice.prac.member.Grade;
import practice.prac.member.Member;
import practice.prac.member.MemberService;
import practice.prac.order.Order;
import practice.prac.order.OrderService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    ApplicationContext ac;
    @BeforeEach
    void setUp() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @DisplayName("RateDiscountPolicy 테스트")
    void discount() {
        Member memeber1 = new Member(1l,"jjun1", Grade.BASIC);
        Member memeber2 = new Member(2l,"jjun2", Grade.VIP);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        memberService.join(memeber1);
        memberService.join(memeber2);

        Order order1 = orderService.createOrder(memeber1.getId(), "pen", 10000);
        Order order2 = orderService.createOrder(memeber2.getId(), "pen", 20000);
        System.out.println("order1 = " + order1.toString());
        System.out.println("order2 = " + order2.toString());

        assertThat(order1.calculatePrice()).isEqualTo(10000);
        assertThat(order2.calculatePrice()).isEqualTo(18000);
    }
}
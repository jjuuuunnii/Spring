package practice.prac.order;

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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    ApplicationContext ac;

    @BeforeEach
    void setUp() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @DisplayName("createOrder 테스트")
    void createOrder() {
        Member member = new Member(1L,"juun", Grade.VIP);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        memberService.join(member);

        Order order1 = orderService.createOrder(member.getId(), "pencil", 10000);
        System.out.println("order1 = " + order1.toString());

        assertThat(order1.calculatePrice()).isEqualTo(9000);

    }
}
package practice.prac.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.prac.AppConfig;
import practice.prac.member.*;

public class OrderServiceTest {
    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        orderService= applicationContext.getBean("orderService", OrderService.class);
        memberService= applicationContext.getBean("memberService", MemberService.class);

    }


    @Test
    void createOrder(){
        Member member1 = new Member(1L, "jjun", Grade.BASIC);
        Member member2 = new Member(2L, "seung", Grade.VIP);

        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(member1.getId(), "juice", 2000);
        Order order2 = orderService.createOrder(member2.getId(), "coffee", 3000);

        Assertions.assertThat(order1.calculatePrice()).isEqualTo(2000);
        Assertions.assertThat(order2.calculatePrice()).isEqualTo(2700);
    }

}

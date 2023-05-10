package practice.prac.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.prac.AppConfig;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
    }

    @Test
    void join(){
        Member member = new Member(1L, "jjun", Grade.VIP);
        memberService.join(member);
        Assertions.assertThat(memberService.findMember(1L).getId()).isEqualTo(1L);
    }

    @Test
    void findMember(){
        Member member = new Member(2L, "jjjun", Grade.VIP);
        memberService.join(member);
        Member foundMember = memberService.findMember(member.getId());
        Assertions.assertThat(member.getId()).isEqualTo(foundMember.getId());

    }
}

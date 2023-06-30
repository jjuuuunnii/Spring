package practice.prac.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.prac.AppConfig;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    ApplicationContext ac;

    @BeforeEach
    public void beforeEach(){
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @DisplayName("join 테스트")
    void join(){
        Member member = new Member(1L, "jun", Grade.BASIC);
        MemberService memberService = ac.getBean("memberService",MemberService.class);

        memberService.join(member);

        assertThat(member.getId()).isEqualTo(memberService.findById(1L).getId());
    }

    @Test
    @DisplayName("DisplayName 테스트")
    void findById(){
        Member member = new Member(2L, "jjun", Grade.BASIC);
        MemberService memberService = ac.getBean("memberService", MemberService.class);


        memberService.join(member);

        Member foundMember = memberService.findById(2L);

        assertThat(foundMember.getId()).isEqualTo(member.getId());
    }


}
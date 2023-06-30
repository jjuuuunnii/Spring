package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트가 끝나면 전부 다시 롤백해주는 어노테이션!! 테스트는 계속 쓸수 있어야하기 때문이지!! 크크 테스트 메서드마다 실행하는거야
    //테스트에만 적용하는 친구양
class MemberServiceIntegrationTest {
    /* @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;  test 케이스는  그냥 autowired 바로 해도 된다.*/
    MemberRepository memberRepository;
    MemberService memberService;

    @Autowired
    public MemberServiceIntegrationTest(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}

//: ctrl + shift + T : 자동으로 테스트를 만들어 주는 단축키
//ctrl + alt + v : 자동으로 리턴 값 완성
//alt + enter : 음,, 앞에 클래스를 import해서 편하게 쓰기,,?
//shift + F6 : 복사하고 중복되는 번호 바꿔주기?
//shift + F10 : 이전에 실행했던거 바로 다시 실행하기
//Alt + insert : 자동으로 클래스 필요한거 생성하기
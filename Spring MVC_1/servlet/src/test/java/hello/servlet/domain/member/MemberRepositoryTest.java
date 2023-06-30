package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setUsername("강승준");
        member.setAge(20);

        //when
        memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);


    }

    @Test
    void findAll() {
        //Given
        Member member1 = new Member("member1", 25);
        Member member2 = new Member("member2", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //When
        List<Member> result = memberRepository.findAll();

        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);


    }

}
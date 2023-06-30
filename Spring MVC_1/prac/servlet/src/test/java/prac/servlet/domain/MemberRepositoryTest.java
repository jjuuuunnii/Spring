package prac.servlet.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(savedMember1.getId());
        Member findMember2 = memberRepository.findById(savedMember2.getId());

        assertThat(savedMember1).isSameAs(findMember1);
        assertThat(savedMember2).isSameAs(findMember2);

    }

    @Test
    void findAll() {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        Member member3 = new Member("Member3", 30);

        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);
        Member savedMember3 = memberRepository.save(member3);

        ArrayList allMember = memberRepository.findAll();
        assertThat(allMember.size()).isEqualTo(3);
        assertThat(allMember).contains(member1, member2, member3);


    }

}
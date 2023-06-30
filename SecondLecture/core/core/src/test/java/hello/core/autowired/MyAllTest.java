package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class MyAllTest {

    @Test
    void allTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountConfig.class);
        DiscountConfig discountConfig = ac.getBean(DiscountConfig.class);
        discountConfig.pracPrintAll();

        Member member1 = new Member(1L, "jjun", Grade.VIP);
        Member member2 = new Member(2L, "min", Grade.VIP);

        int fixDiscountPolicy = discountConfig.discountPrice(member1, 10000, "fixDiscountPolicy");
        int rateDiscountPolicy = discountConfig.discountPrice(member2, 20000, "rateDiscountPolicy");

        Assertions.assertThat(fixDiscountPolicy).isEqualTo(1000);
        Assertions.assertThat(rateDiscountPolicy).isEqualTo(2000);


    }
    static class DiscountConfig{
        private Map<String, DiscountPolicy> pracPolicyMap;
        private List<DiscountPolicy> pracPolicyList;

        @Autowired
        public DiscountConfig(Map<String, DiscountPolicy> pracPolicyMap, List<DiscountPolicy> pracPolicyList) {
            this.pracPolicyMap = pracPolicyMap;
            this.pracPolicyList = pracPolicyList;
        }

        public void pracPrintAll(){
            System.out.println("pracPolicyMap = " + pracPolicyMap);
            System.out.println("pracPolicyList = " + pracPolicyList);
        }

        public int discountPrice(Member member, int price,String discountCode){
            DiscountPolicy discountPolicy = pracPolicyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }

    }
}

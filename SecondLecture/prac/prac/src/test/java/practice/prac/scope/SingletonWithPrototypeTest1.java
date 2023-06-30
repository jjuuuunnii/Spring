package practice.prac.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        System.out.println("clientBean1 = " + clientBean1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        System.out.println("clientBean2 = " + clientBean2);

        int count1 = clientBean1.logic();

        int count2 = clientBean2.logic();

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);


    }


//    @RequiredArgsConstructor
    static class ClientBean{

//        private final PrototypeBean prototypeBean;

       @Autowired
       private ObjectProvider <PrototypeBean> objectProvider;

       public int logic(){
           System.out.println("in logic start");
           PrototypeBean prototypeBean = objectProvider.getObject();
           System.out.println("prototypeBean = " + prototypeBean);
           prototypeBean.addCount();
           int count = prototypeBean.getCount();
           return count;
       }

    }


    @Scope("prototype")
    static class PrototypeBean{
        private int count =0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("PrototypeBean.close");
        }
    }
}

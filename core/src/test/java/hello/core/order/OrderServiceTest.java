package hello.core.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OrderServiceTest {

    ApplicationContext ac            = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService      memberService = ac.getBean("memberService", MemberService.class);
    OrderService       orderService  = ac.getBean("orderService", OrderService.class);

    @Test
    void createOrder() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertEquals(order.getDiscountPrice(), 1000);
    }

    @Test
    void filedInjectionTest() {
        OrderServiceImpl orderService     = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = orderService.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        ApplicationContext ac            = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService      memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.printf("new member: %s\n\r", findMember.getName());
        System.out.printf("new member: %s", findMember.getName());
    }

    private static Class<MemberService> getRequiredType() {
        return MemberService.class;
    }
}

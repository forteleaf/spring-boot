package hello.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    void memberTest() {
        Member member = new Member("memberA", "회원A");
        memberRepository.initTable();
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getMemberId());
        Assertions.assertEquals(member.getMemberId(), findMember.getMemberId());
        Assertions.assertEquals(member.getName(), findMember.getName());
    }
}
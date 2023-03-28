package hello.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    public final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initTable() {
        jdbcTemplate.execute("create table member (member_id varchar(255) not null, name varchar(255), primary key (member_id))");
    }

    public void save(Member member) {
        jdbcTemplate.update("insert into member (member_id, name) values (?, ?)", member.getMemberId(), member.getName());
    }

    public Member findById(String memberId) {
        return jdbcTemplate.queryForObject("select * from member where member_id = ?", BeanPropertyRowMapper.newInstance(Member.class), memberId);
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("select member_id, name from member", BeanPropertyRowMapper.newInstance(Member.class));
    }
}

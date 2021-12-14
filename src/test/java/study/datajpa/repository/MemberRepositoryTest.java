package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository MemberRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = MemberRepository.save(member);

        Member findMember = MemberRepository.findById(savedMember.getId())
                                            .get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        MemberRepository.save(member1);
        MemberRepository.save(member2);

        Member findMember1 = MemberRepository.findById(member1.getId())
                                             .get();
        Member findMember2 = MemberRepository.findById(member2.getId())
                                             .get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = MemberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = MemberRepository.count();
        assertThat(count).isEqualTo(2);

        MemberRepository.delete(member1);
        MemberRepository.delete(member2);

        long deletedCount = MemberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}
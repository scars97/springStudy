package hello.hellosping.repository;

import hello.hellosping.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //Entity 설정으로 구현코드 훨씬 줄어듦. 
    //primary key로 지정되지 않은 컬럼들은 jpql 짜줘야함
    //jpql은 컬럼 하나를 설정하지않고 객체 자체를 설정
    
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {//객체 자체를 셀렉트
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}

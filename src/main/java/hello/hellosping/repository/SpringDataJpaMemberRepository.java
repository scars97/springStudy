package hello.hellosping.repository;

import hello.hellosping.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //알아서 구현체를 만들고 스프링 빈에 저장함
    //인터페이스만으로도 구현가능....
    //Jpa를 더 편하게 사용하기 위한 기술
    //Jpa를 기본적으로 다 알고 사용해야 함.
    @Override
    Optional<Member> findByName(String name);
}

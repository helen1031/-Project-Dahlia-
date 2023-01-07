package project.dahlia.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dahlia.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByMemberId(String memberId);
}

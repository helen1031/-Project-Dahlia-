package project.dahlia.member.entity;

import lombok.Getter;
import lombok.Setter;
import project.dahlia.member.dto.MemberDTO;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 10)
    private String memberName;

    @Column(length = 20)
    private String memberBirth;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 30, unique = true)
    private String memberPhone;

    @Column(length = 20, unique = true)
    private String memberId;

    @Column(length = 20)
    private String memberPassword;


    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        return memberEntity;
    }
}

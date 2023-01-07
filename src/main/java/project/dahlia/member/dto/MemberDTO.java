package project.dahlia.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.dahlia.member.entity.MemberEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberName;
    private String memberBirth;
    private String memberEmail;
    private String memberPhone;
    private String memberId;
    private String memberPassword;

    public MemberDTO(String memberName, String memberBirth, String memberEmail, String memberPhone, String memberId, String memberPassword) {
        this.memberName = memberName;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        return memberDTO;
    }
}

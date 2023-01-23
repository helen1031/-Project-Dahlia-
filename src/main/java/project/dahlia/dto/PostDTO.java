package project.dahlia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.dahlia.model.PostEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private String id;

    private String flowerName;

    // 색상은 코드로 관리하며(FlowerColor enum), 최대 3개까지 선택 가능
    private int flowerColor1;
    private int flowerColor2;
    private int flowerColor3;

    private String flowerFeature;

    // 이미지는 최대 5장까지 업로드 가능
    private String flowerImg1;
    private String flowerImg2;
    private String flowerImg3;
    private String flowerImg4;
    private String flowerImg5;

    private int flowerQuantity;

    // 해당 게시물의 공개범위 설정 : 본인, 이웃, 전체공개
    private int disclosureScope;

    public PostDTO(final PostEntity entity) {
        this.id = entity.getId();
        this.flowerName = entity.getFlowerName();
        this.flowerColor1 = entity.getFlowerColor1();
        this.flowerColor2 = entity.getFlowerColor2();
        this.flowerColor3 = entity.getFlowerColor3();
        this.flowerFeature = entity.getFlowerFeature();
        this.flowerImg1 = entity.getFlowerImg1();
        this.flowerImg2 = entity.getFlowerImg2();
        this.flowerImg3 = entity.getFlowerImg3();
        this.flowerImg4 = entity.getFlowerImg4();
        this.flowerImg5 = entity.getFlowerImg5();
        this.flowerQuantity = entity.getFlowerQuantity();
        this.disclosureScope = entity.getDisclosureScope();
    }

    public static PostEntity toEntity(final PostDTO dto) {
        return PostEntity.builder()
                .id(dto.getId())
                .flowerName(dto.getFlowerName())
                .flowerColor1(dto.getFlowerColor1())
                .flowerColor2(dto.getFlowerColor2())
                .flowerColor3(dto.getFlowerColor3())
                .flowerFeature(dto.getFlowerFeature())
                .flowerImg1(dto.getFlowerImg1())
                .flowerImg2(dto.getFlowerImg2())
                .flowerImg3(dto.getFlowerImg3())
                .flowerImg4(dto.getFlowerImg4())
                .flowerImg5(dto.getFlowerImg5())
                .flowerQuantity(dto.getFlowerQuantity())
                .disclosureScope(dto.getDisclosureScope())
                .build();
    }
}

package project.dahlia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Post")
public class FlowerPostEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String userId;

    private String flowerName;

    // 색상은 코드로 관리하며(FlowerColor enum), 최대 3개까지 선택 가능
    private int flowerColor1;
    private int flowerColor2;
    private int flowerColor3;

    // 키는 코드로 관리한다(enum)
    private int flowerHeight;

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
}

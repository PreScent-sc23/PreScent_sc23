//package Unknown.PreScent.entity;
//
//import Unknown.PreScent.dto.CustomizeProductDto;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "customize-product")
//public class CustomizeProductEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer orderNum;
//    private Integer cpKey;
//    private List<String> answers;
//    private String orderTime;
//    private boolean isAccept;
//
//    public static CustomizeProductEntity customizeProductDtoToEntity(CustomizeProductDto customizeProductDto){
//        CustomizeProductEntity customizeProductEntity = new CustomizeProductEntity();
////        customizeProductEntity.setOrderNum(customizeProductDto.getOrderNum());
//        customizeProductEntity.setCpKey(customizeProductDto.getCpKey());
//        customizeProductEntity.setAnswers(customizeProductDto.getAnswers());
//        customizeProductEntity.setOrderTime(customizeProductDto.getOrderTime());
//        customizeProductEntity.setAccept(customizeProductDto.isAccept());
//
//        return customizeProductEntity;
//    }
//}

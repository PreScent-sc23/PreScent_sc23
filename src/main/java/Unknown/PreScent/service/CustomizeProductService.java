//package Unknown.PreScent.service;
//
//import Unknown.PreScent.dto.CustomizeProductDto;
//import Unknown.PreScent.entity.CustomizeProductEntity;
//import Unknown.PreScent.repository.CustomizeProductRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomizeProductService {
//    final CustomizeProductRepository customizeProductRepo;
//
//    public CustomizeProductService(CustomizeProductRepository customizeProductRepo) {
//        this.customizeProductRepo = customizeProductRepo;
//    }
//
//    public void addCustomizedProduct(CustomizeProductDto customizeProductDto){
//        CustomizeProductEntity customizeProductEntity = CustomizeProductEntity.customizeProductDtoToEntity(customizeProductDto);
//
//        customizeProductRepo.save(customizeProductEntity);
//    }
//}

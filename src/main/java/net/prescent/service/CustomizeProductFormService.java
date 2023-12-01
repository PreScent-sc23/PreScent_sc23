//package Unknown.PreScent.service;
//
//import Unknown.PreScent.dto.CustomizeProductFormDto;
//import Unknown.PreScent.entity.CustomizeProductFormEntity;
//import Unknown.PreScent.repository.CustomizeProductFormRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomizeProductFormService {
//
//    final CustomizeProductFormRepository customizeProductFormRepo;
//
//    public CustomizeProductFormService(CustomizeProductFormRepository customizeProductFormRepository) {
//        this.customizeProductFormRepo = customizeProductFormRepository;
//    }
//
//    public void addCustomizeProductForm(CustomizeProductFormDto customizeProductFormDto){
//        CustomizeProductFormEntity customizeProductFormEntity = CustomizeProductFormEntity.customizeProductFormDtoToEntity(customizeProductFormDto);
//        customizeProductFormRepo.save(customizeProductFormEntity);
//    }
//}

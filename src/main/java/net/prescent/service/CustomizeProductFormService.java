//package net.prescent.service;
//
//import net.prescent.dto.CustomizeProductFormDto;
//import net.prescent.entity.CustomizeProductFormEntity;
//import net.prescent.repository.CustomizeProductFormRepository;
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

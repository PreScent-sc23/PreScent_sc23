package Unknown.PreScent.service;

import Unknown.PreScent.dto.FPOrderCustomerDto;
import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.entity.FPOrderEntity;
import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.repository.CustomerRepository;
import Unknown.PreScent.repository.FPOrderRepository;
import Unknown.PreScent.repository.FinishedProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FPOrderService {
    final FPOrderRepository fpOrderRepo;
    final FinishedProductRepository finishedProductRepo;
    final CustomerRepository customerRepo;

    public FPOrderService(FPOrderRepository fpOrderRepo, FinishedProductRepository finishedProductRepo, CustomerRepository customerRepo) {
        this.fpOrderRepo = fpOrderRepo;
        this.finishedProductRepo = finishedProductRepo;
        this.customerRepo = customerRepo;
    }


    public FPOrderEntity customerOrderFinishedProduct(FPOrderCustomerDto fpOrderCustomerDto)
    {

        FPOrderEntity fpOrderEntity = this.preFPOrderCustomerDtoToFPOrderEntity(fpOrderCustomerDto); //이름 어떡할지 모르곘음
        fpOrderRepo.save(fpOrderEntity);
        finishedProductRepo.save(fpOrderEntity.getFinishedProductEntity());
        customerRepo.save(fpOrderEntity.getCustomerEntity());

        System.out.println("==================================== fporderEntity저장도 끝+++++++++"+fpOrderEntity);
        return fpOrderEntity;
        // Dto로 만들어서 보내야 하나? Entity 모든 필드 정보를 보내야 해서 Dto만들어도 entity와 같은 필드.
    }

    public FPOrderEntity preFPOrderCustomerDtoToFPOrderEntity(FPOrderCustomerDto fpOrderCustomerDto)
    {
        FPOrderEntity fpOrderEntity = new FPOrderEntity();
        System.out.println("=----------------------------------------------------------------preFPOrderToEntity내부 체크 시작)"+fpOrderCustomerDto.getFpKey());
        Optional<FinishedProductEntity> finishedProductEntity = finishedProductRepo.findByFpKey(fpOrderCustomerDto.getFpKey());
        System.out.println("=----------------------------------------------------------------preFPOrderToEntity내부 findByKey실행완료)");
        if(!finishedProductEntity.isPresent())
        {
            throw new IllegalStateException("주문하려고 하는 제품이 존재하지 않는 제품입니다.");
        }

        Optional<CustomerEntity> customerEntity = customerRepo.findByCustomerKey(fpOrderCustomerDto.getCustomerKey());
        System.out.println("=----------------------------------------------------------------preFPOrderToEntity내부 findByCustomerKey실행완료)");
        if(!customerEntity.isPresent())
        {
            throw new IllegalStateException("주문하려는 고객의 정보가 존재하지 않습니다.");
        }
        System.out.println("=----------------------------------------------------------------preFPOrderToEntity내부 체크까진 끝)");
        return fpOrderEntity.FPOrderCustomerDtoToFPOrderEntity(finishedProductEntity.get(), customerEntity.get(), fpOrderCustomerDto.getPurchaseInfo(), fpOrderCustomerDto.getPickupDate());
    }

}
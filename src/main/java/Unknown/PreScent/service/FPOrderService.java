package Unknown.PreScent.service;

import Unknown.PreScent.dto.FPOrderCustomerDto;
import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.entity.FPOrderEntity;
import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.repository.CustomerRepository;
import Unknown.PreScent.repository.FPOrderRepository;
import Unknown.PreScent.repository.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FPOrderService {
    private FPOrderRepository fpOrderRepo;
    private FinishedProductRepository finishedProductRepo;
    private CustomerRepository customerRepo;


    public FPOrderEntity customerOrderFinishedProduct(FPOrderCustomerDto fpOrderCustomerDto)
    {

        FPOrderEntity fpOrderEntity = this.preFPOrderCustomerDtoToFPOrderEntity(fpOrderCustomerDto); //이름 어떡할지 모르곘음
        fpOrderRepo.save(fpOrderEntity);
        finishedProductRepo.save(fpOrderEntity.getFinishedProductEntity());
        customerRepo.save(fpOrderEntity.getCustomerEntity());

        return fpOrderEntity;
        // Dto로 만들어서 보내야 하나? Entity 모든 필드 정보를 보내야 해서 Dto만들어도 entity와 같은 필드.
    }

    public FPOrderEntity preFPOrderCustomerDtoToFPOrderEntity(FPOrderCustomerDto fpOrderCustomerDto)
    {
        FPOrderEntity fpOrderEntity = new FPOrderEntity();

        Optional<FinishedProductEntity> finishedProductEntity = finishedProductRepo.findByFpKey(fpOrderCustomerDto.getFpKey());
        if(!finishedProductEntity.isPresent())
        {
            throw new IllegalStateException("주문하려고 하는 제품이 존재하지 않는 제품입니다.");
        }

        Optional<CustomerEntity> customerEntity = customerRepo.findByCustomerKey(fpOrderCustomerDto.getCustomerKey());
        if(!customerEntity.isPresent())
        {
            throw new IllegalStateException("주문하려는 고객의 정보가 존재하지 않습니다.");
        }
        return fpOrderEntity.FPOrderCustomerDtoToFPOrderEntity(finishedProductEntity.get(), customerEntity.get(), fpOrderCustomerDto.getPurchaseInfo(), fpOrderCustomerDto.getPickupDate());
    }

}

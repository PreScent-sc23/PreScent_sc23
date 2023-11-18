package Unknown.PreScent.service;

import Unknown.PreScent.dto.FPOrderCustomerDto;
import Unknown.PreScent.entity.CustomerEntity;
import Unknown.PreScent.entity.FPOrderEntity;
import Unknown.PreScent.repository.CustomerRepository;
import Unknown.PreScent.repository.FPOrderRepository;
import Unknown.PreScent.repository.FinishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FPOrderService {
    @Autowired
    private FPOrderRepository fpOrderRepo;
    @Autowired
    private FinishedProductRepository finishedProductRepo;
    @Autowired
    private CustomerRepository customerRepo;


    public FPOrderEntity customerOrderFinishedProduct(FPOrderCustomerDto fpOrderCustomerDto)
    {

        FPOrderEntity fpOrderEntity = FPOrderEntity.FPOrderSellerDtoToFPOrderEntity(fpOrderCustomerDto);
        fpOrderRepo.save(fpOrderEntity);
        finishedProductRepo.save(fpOrderEntity.getFinishedProductEntity());
        customerRepo.save(fpOrderEntity.getCustomerEntity());

        return fpOrderEntity;
        // Dto로 만들어서 보내야 하나? Entity 모든 필드 정보를 보내야 해서 Dto만들어도 entity와 같은 필드.
    }

}

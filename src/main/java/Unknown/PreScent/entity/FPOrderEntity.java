package Unknown.PreScent.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

import Unknown.PreScent.dto.FPOrderCustomerDto;
import Unknown.PreScent.repository.CustomerRepository;
import Unknown.PreScent.repository.FinishedProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Entity
public class FPOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpOrderKey;

    @ManyToOne
    @JoinColumn(name = "fpKey")
    FinishedProductEntity finishedProductEntity;

    @ManyToOne
    @JoinColumn(name ="customerKey")
    CustomerEntity customerEntity;

    @Column(nullable = false)
    private String purchaseInfo;
    @Column(nullable = false)
    private Date pickupDate;
    @Column(nullable = false)
    private Byte fpOrderState=0;

    private void setFinishedProductEntity(FinishedProductEntity finishedProductEntity)
    {
        this.finishedProductEntity = finishedProductEntity;
        this.finishedProductEntity.setFpOrderEntity(this);
    }

    private void setCustomerEntity(CustomerEntity customerEntity)
    {
        this.customerEntity = customerEntity;
        this.customerEntity.setFpOrderEntity(this);
    }

    public static FPOrderEntity FPOrderSellerDtoToFPOrderEntity(FPOrderCustomerDto fpOrderCustomerDto)
    {
        FPOrderEntity fpOrderEntity = new FPOrderEntity();

        final FinishedProductRepository finishedProductRepository = null;
        Optional<FinishedProductEntity> finishedProductEntity = finishedProductRepository.findByFpKey(fpOrderCustomerDto.getFpKey());
        if(!finishedProductEntity.isPresent())
        {
            throw new IllegalStateException("주문하려고 하는 제품이 존재하지 않는 제품입니다.");
        }
        fpOrderEntity.setFinishedProductEntity(finishedProductEntity.get());

        final CustomerRepository customerRepository = null;
        Optional<CustomerEntity> customerEntity = customerRepository.findByCustomerKey(fpOrderCustomerDto.getCustomerKey());
        if(!customerEntity.isPresent())
        {
            throw new IllegalStateException("주문하려는 고객의 정보가 존재하지 않습니다.");
        }
        fpOrderEntity.setCustomerEntity(customerEntity.get());

        fpOrderEntity.setPurchaseInfo(fpOrderCustomerDto.getPurchaseInfo());
        fpOrderEntity.setPickupDate(fpOrderCustomerDto.getPickupDate());

        return fpOrderEntity;
    }
}
package Unknown.PreScent.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

import Unknown.PreScent.dto.CustomerDto;
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
        this.finishedProductEntity.setFpOrderEntityList(this);
    }

    private void setCustomerEntity(CustomerEntity customerEntity)
    {
        this.customerEntity = customerEntity;
        this.customerEntity.setFpOrderEntityList(this);
    }

    public FPOrderEntity FPOrderCustomerDtoToFPOrderEntity(FinishedProductEntity finishedProductEntity, CustomerEntity customerEntity, String purchaseInfo, Date pickupDate)
    {
        FPOrderEntity fpOrderEntity = new FPOrderEntity();

        fpOrderEntity.setFinishedProductEntity(finishedProductEntity);
        fpOrderEntity.setCustomerEntity(customerEntity);
        fpOrderEntity.setPurchaseInfo(purchaseInfo);
        fpOrderEntity.setPickupDate(pickupDate);
        System.out.println("=----------------------------------------------------------------FPOrderToEntity 내부 set까지 끝)");

        return fpOrderEntity;
    }
}
package Unknown.PreScent.entity;

import Unknown.PreScent.dto.CustomerDto;
import Unknown.PreScent.dto.SellerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "customer")

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerKey;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false, unique = true)
    private String customerIdEmail;
    @Column(nullable = false)
    private String customerPassword;
    @Column(nullable = false)
    private String customerPhonenum;
    private String customerLocation;



    public static CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerKey(customerDto.getCustomerKey());
        customerEntity.setCustomerName(customerDto.getCustomerName());
        customerEntity.setCustomerPhonenum(customerDto.getCustomerPhonenum());
        customerEntity.setCustomerIdEmail(customerDto.getCustomerIdEmail());
        customerEntity.setCustomerPassword(customerDto.getCustomerPassword());
        customerEntity.setCustomerLocation(customerDto.getCustomerLocation());
        return customerEntity;
    }
}

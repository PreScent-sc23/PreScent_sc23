package net.prescent.dto;

import net.prescent.entity.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "customerPassword")
public class CustomerDto {

    private Integer customerKey;
    @NotBlank(message = "이름을 작성해주세요")
    private String customerName;
    @Email(message = "올바른 이메일 형식을 사용해주세요")
    @NotBlank(message = "이메일을 작성해주세요")
    private String customerIdEmail;
    @NotBlank(message = "비밀번호를 작성해주세요")
    private String customerPassword;
    @NotBlank(message = "전화번호를 작성해주세요")
    private String customerPhonenum;
    private String customerLocation;


    public static CustomerDto toCustomerDto(CustomerEntity customerEntity){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerKey(customerEntity.getCustomerKey());
        customerDto.setCustomerName(customerEntity.getCustomerName());
        customerDto.setCustomerIdEmail(customerEntity.getCustomerIdEmail());
        customerDto.setCustomerPhonenum(customerEntity.getCustomerPhonenum());
        customerDto.setCustomerPassword(customerEntity.getCustomerPassword());
        customerDto.setCustomerLocation(customerEntity.getCustomerLocation());
        return customerDto;
    }
}

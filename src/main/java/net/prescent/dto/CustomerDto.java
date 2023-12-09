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
@ToString(exclude = "password")
public class CustomerDto {

    private Integer userKey;
    @NotBlank(message = "이름을 작성해주세요")
    private String name;
    @Email(message = "올바른 이메일 형식을 사용해주세요")
    @NotBlank(message = "이메일을 작성해주세요")
    private String idEmail;
    @NotBlank(message = "비밀번호를 작성해주세요")
    private String password;
    private String confirmPassword;
    @NotBlank(message = "전화번호를 작성해주세요")
    private String phonenum;
    private String address;
    private Double latitude;
    private Double longitude;




    public static CustomerDto toCustomerDto(CustomerEntity customerEntity){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customerEntity.getName());
        customerDto.setIdEmail(customerEntity.getIdEmail());
        customerDto.setPhonenum(customerEntity.getPhonenum());
        customerDto.setAddress(customerEntity.getAddress());
        if(customerEntity.getLatitude()!=null) customerDto.setLatitude(customerEntity.getLatitude());
        if(customerEntity.getLongitude()!=null) customerDto.setLongitude(customerEntity.getLongitude());
        return customerDto;
    }

}

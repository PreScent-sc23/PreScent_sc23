package net.prescent.entity;

import net.prescent.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="cart_id")
//    private CartEntity cart;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String idEmail;

    @Column(nullable = false)
    private String phonenum;

    private String location;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<FPOrderEntity> fpOrderEntityList = new ArrayList<>();



    public static CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(customerDto.getName());
        customerEntity.setPhonenum(customerDto.getPhonenum());
        customerEntity.setIdEmail(customerDto.getIdEmail());
        customerEntity.setPassword(customerDto.getPassword());
        customerEntity.setLocation(customerDto.getLocation());
        return customerEntity;
    }

    public void setFpOrderEntityList(FPOrderEntity fpOrderEntity) {
        if(this.fpOrderEntityList == null)
        {
            this.fpOrderEntityList = new ArrayList<>();
        }
        this.fpOrderEntityList.add(fpOrderEntity);
    }
    @Override
    public String getUserType() {
        return "Customer";
    }

//    public static FinishedProductEntity createFp(CartEntity cart, Item item, int amount) {
//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setItem(item);
//        cartItem.setCount(amount);
//        return cartItem;
//    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
//    public void addCount(int count) {
//        this.count += count;
//    }

}
package net.prescent.entity;

import net.prescent.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private CartEntity cart;

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

        customerEntity.setCustomerName(customerDto.getCustomerName());
        customerEntity.setCustomerPhonenum(customerDto.getCustomerPhonenum());
        customerEntity.setCustomerIdEmail(customerDto.getCustomerIdEmail());
        customerEntity.setCustomerPassword(customerDto.getCustomerPassword());
        customerEntity.setCustomerLocation(customerDto.getCustomerLocation());
        return customerEntity;
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
package net.prescent.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartKey;

    @OneToOne(mappedBy = "cartEntity",fetch = FetchType.EAGER)
    private CustomerEntity customerEntity; // 구매자

    @OneToMany
    @JoinColumn(name = "cartItemEntityList")
    private List<CartItemEntity> cartItemEntityList = new ArrayList<>();

    private Integer totalCount;
    private Integer totalPrice;

//    @DateTimeFormat(pattern = "yyyy-mm-dd")
//    private LocalDate createDate; // 날짜
//
//    @PrePersist
//    public void createDate(){
//        this.createDate = LocalDate.now();
//    }
    public CartEntity(CustomerEntity customerEntity, Integer totalCount, Integer totalPrice)
    {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCustomerEntity(customerEntity);
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
    }

    public void setCustomerEntity(CustomerEntity customerEntity)
    {
        this.customerEntity = customerEntity;
        customerEntity.setCartEntity(this);
    }
}

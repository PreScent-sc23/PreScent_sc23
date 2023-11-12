package Unknown.PreScent.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count; // 카트에 담긴 총 상품 개수

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private CustomerEntity customer; // 구매자

    @OneToMany(mappedBy = "cart")
    private List<FinishedProductEntity> finishedProductEntityList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist
    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public static CartEntity createCart(CustomerEntity customer){
        CartEntity cart = new CartEntity();
        cart.setCount(0);
        cart.setCustomer(customer);
        return cart;
    }

}

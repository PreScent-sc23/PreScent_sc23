package net.prescent.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemKey;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fpKey")
    private FinishedProductEntity finishedProductEntity;

    private int count; // 상품 개수

    public static CartItemEntity createCartItem(CartEntity cart, FinishedProductEntity fp, Integer amount) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setFinishedProductEntity(fp);
        cartItemEntity.setCount(amount);
        return cartItemEntity;
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(Integer count) {
        this.count += count;
    }

}

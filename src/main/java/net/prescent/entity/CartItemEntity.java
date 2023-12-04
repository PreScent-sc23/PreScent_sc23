package net.prescent.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



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
    private String pickupDate;
    private String pickupTime;
    private Integer price;

    private int count; // 상품 개수

    public static CartItemEntity createCartItem(FinishedProductEntity fp, Integer amount, String pickupDate, String pickupTime) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setFinishedProductEntity(fp);
        cartItemEntity.setCount(amount);
        cartItemEntity.setPickupDate(pickupDate);
        cartItemEntity.setPickupTime(pickupTime);
        cartItemEntity.setPrice(fp.getFpPrice());
        return cartItemEntity;
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(Integer count) {
        this.count += count;
    }

}

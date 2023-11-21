package net.prescent.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="finished_product_id")
    private FinishedProductEntity finishedProduct;

    private int count; // 상품 개수

    public static CartItemEntity createCartItem(CartEntity cart, FinishedProductEntity fp, Integer amount) {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setFinishedProduct(fp);
        cartItem.setCount(amount);
        return cartItem;
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(Integer count) {
        this.count += count;
    }

}

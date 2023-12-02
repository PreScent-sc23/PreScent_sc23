package net.prescent.service;

import net.prescent.entity.CartEntity;
import net.prescent.entity.CartItemEntity;
import net.prescent.entity.CustomerEntity;
import net.prescent.repository.CartItemRepository;
import net.prescent.repository.CartRepository;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.FinishedProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService {
    private FinishedProductRepository finishedProductRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final CustomerEntity customerEntity;

    public CartService(FinishedProductRepository finishedProductRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, UserService userService, CustomerRepository customerRepository, CustomerEntity customerEntity) {
        this.finishedProductRepository = finishedProductRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.customerEntity = customerEntity;
    }
    public void addCartItem(Integer custKey, Integer fpKey, Integer amount){
        // 나중엔 토큰으로 받으니까 custDto로 받아와도 될 듯
        Optional<CustomerEntity> foundCustomerEntity = customerRepository.findByUserKey(custKey);
        if(foundCustomerEntity.isPresent())
        {
            CustomerEntity customerEntity = foundCustomerEntity.get();
            CartEntity cartEntity = customerEntity.getCartEntity();
            if(cartEntity == null)
            {
                cartEntity = new CartEntity(customerEntity, 0, 0);

            }
        }
        else
        {
            throw new IllegalStateException("장바구니를 소유한 customer를 찾을 수 없습니다.");
        }












//        Optional<CartEntity> cart = cartRepository.findByCustomerKey(customerDto.getCustomerKey());
//
//        if(cart.isEmpty()){
//            cart = Optional.of(CartEntity.createCart(CustomerEntity.toCustomerEntity(customerDto)));
//            cartRepository.save(cart.get());
//        }
//
//        Optional<FinishedProductEntity> foundFp = finishedProductRepository.findByFpKey(newFinishedProductEntity.getFpKey());
//        Optional<CartItemEntity> foundCartItem = cartItemRepository.findByCartKeyAndFpKey(cart.get().getCartKey(), newFinishedProductEntity.getFpKey());
//
//        if(foundCartItem.isEmpty()){
//            foundCartItem = Optional.of(CartItemEntity.createCartItem(cart.get(), foundFp.get(), amount));
//            cartItemRepository.save(foundCartItem.get());
//        }
//
//        else{
//            increaseItemN(amount, foundCartItem.get());
//        }
//
//        cart.get().setCount(cart.get().getCount() + amount);
    }

    private void increaseItemN(Integer amount, CartItemEntity foundCartItem) {
        CartItemEntity update = foundCartItem;
        update.setCart(foundCartItem.getCart());
        update.setFinishedProduct(foundCartItem.getFinishedProduct());
        update.addCount(amount);
        update.setCount(update.getCount());
        cartItemRepository.save(update);
    }


}

//package Unknown.PreScent.service;
//
//import dto.net.prescent.CustomerDto;
//import Unknown.PreScent.entity.CartEntity;
//import Unknown.PreScent.entity.CartItemEntity;
//import entity.net.prescent.CustomerEntity;
//import entity.net.prescent.FinishedProductEntity;
//import Unknown.PreScent.repository.CartItemRepository;
//import Unknown.PreScent.repository.CartRepository;
//import repository.net.prescent.FinishedProductRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//public class CartService {
//    private FinishedProductRepository finishedProductRepository;
//    private final CartRepository cartRepository;
//    private final CartItemRepository cartItemRepository;
//
//    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository){
//        this.cartRepository = cartRepository;
//        this.cartItemRepository = cartItemRepository;
//    }
//
//    public void addCartItem(CustomerDto customerDto, FinishedProductEntity newFinishedProductEntity, Integer amount){
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
//    }
//
//    private void increaseItemN(Integer amount, CartItemEntity foundCartItem) {
//        CartItemEntity update = foundCartItem;
//        update.setCart(foundCartItem.getCart());
//        update.setFinishedProduct(foundCartItem.getFinishedProduct());
//        update.addCount(amount);
//        update.setCount(update.getCount());
//        cartItemRepository.save(update);
//    }
//
//
//}

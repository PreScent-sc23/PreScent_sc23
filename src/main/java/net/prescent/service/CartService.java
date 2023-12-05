package net.prescent.service;

import net.prescent.dto.CartItemAddRequestDto;
import net.prescent.dto.CartItemResponseDto;
import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.CartEntity;
import net.prescent.entity.CartItemEntity;
import net.prescent.entity.CustomerEntity;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.repository.CartItemRepository;
import net.prescent.repository.CartRepository;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.FinishedProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class CartService {
    private final FinishedProductRepository finishedProductRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final UserService userService;
    private final CustomerRepository customerRepo;

    public CartService(FinishedProductRepository finishedProductRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, UserService userService, CustomerRepository customerRepository) {
        this.finishedProductRepo = finishedProductRepository;
        this.cartRepo = cartRepository;
        this.cartItemRepo = cartItemRepository;
        this.userService = userService;
        this.customerRepo = customerRepository;
    }

    public void calculateTotalPrice(CartEntity cartEntity)
    {
        Integer totalPrice = 0, totalCount = 0;
        if(cartEntity.getCartItemEntityList()!=null)
        {
            for(CartItemEntity cartItemEntity : cartEntity.getCartItemEntityList())
            {
                totalPrice+= cartItemEntity.getPrice();
                totalCount+= cartItemEntity.getCount();
            }
        }
        cartEntity.setTotalPriceAndCount(totalPrice, totalCount);
    }

    //추후에 count올리는 로직도 구현
    public void addToCart(CartItemAddRequestDto cartItemAddRequestDto){
        // 나중엔 토큰으로 받으니까 custDto로 받아와도 될 듯
        Optional<CustomerEntity> foundCustomerEntity = customerRepo.findByUserKey(cartItemAddRequestDto.getUserKey());
        if(foundCustomerEntity.isPresent())
        {
            CustomerEntity customerEntity = foundCustomerEntity.get();
            if(customerEntity.getCartEntity() == null)
            {
                CartEntity cartEntity = new CartEntity(0, 0);
                cartEntity.setCustomerEntity(customerEntity);
                cartRepo.save(cartEntity);
            }
            CartEntity cartEntity = customerEntity.getCartEntity();
            CartItemEntity cartItemEntity = addCartItem(cartItemAddRequestDto);
            cartEntity.setCartItemEntityList(cartItemEntity);
            calculateTotalPrice(cartEntity);
            cartItemRepo.save(cartItemEntity);
            cartRepo.save(cartEntity);
            customerRepo.save(customerEntity);
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
    public CartItemEntity addCartItem(CartItemAddRequestDto cartItemAddRequestDto)
    {
        Optional<FinishedProductEntity> foundFPEntity = finishedProductRepo.findByFpKey(cartItemAddRequestDto.getFpKey());
        if(foundFPEntity.isPresent())
        {
            FinishedProductEntity fpEntity = foundFPEntity.get();
            return CartItemEntity.createCartItem(fpEntity, cartItemAddRequestDto.getAmount(), cartItemAddRequestDto.getPickupDate(), cartItemAddRequestDto.getPickupTime());
        }
        else {
            throw new IllegalStateException("fpKey로 완제품을 찾을 수 없습니다.");
        }
    }

    public CartItemResponseDto entityToCartResponseDto(CartItemResponseDto cartItemResponseDto, FinishedProductEntity finishedProductEntity)
    {
        cartItemResponseDto.setFpKey(finishedProductEntity.getFpKey());
        cartItemResponseDto.setFpImage(finishedProductEntity.getFpImage());
        cartItemResponseDto.setFpName(finishedProductEntity.getFpName());
        cartItemResponseDto.setFpTag(finishedProductEntity.getFpTag());
        cartItemResponseDto.setFpPrice(finishedProductEntity.getFpPrice());
        cartItemResponseDto.setFpDetail(finishedProductEntity.getFpDetail());
        cartItemResponseDto.setFpFlowerList(finishedProductEntity.getFpFlowerList());
        return cartItemResponseDto;
    }
    public List<CartItemResponseDto> viewInCart(Integer userKey) {
        Optional<CustomerEntity> foundCustomerEntity =customerRepo.findByUserKey(userKey);
        if(foundCustomerEntity.isPresent())
        {
            CustomerEntity customerEntity = foundCustomerEntity.get();

            if(customerEntity.getCartEntity() !=null)
            {CartEntity cartEntity = customerEntity.getCartEntity();
                if(cartEntity.getCartItemEntityList()!=null)
                {
                    List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
                    for(CartItemEntity cartItemEntity: cartEntity.getCartItemEntityList())
                    {
                        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
                        cartItemResponseDto.setCartItemKey(cartItemEntity.getCartItemKey());
                        cartItemResponseDto.setCount(cartItemResponseDto.getCount());
                        FinishedProductEntity finishedProductEntity = cartItemEntity.getFinishedProductEntity();
                        entityToCartResponseDto(cartItemResponseDto, finishedProductEntity);
                        cartItemResponseDto.setFlowerShopName(finishedProductEntity.getFlowerShopEntity().getShopName());
                        cartItemResponseDtoList.add(cartItemResponseDto);
                    }
                    return cartItemResponseDtoList;
                }
                else {
                    throw new IllegalStateException("Cart가 비어있습니다.");
                }
            }
            else {
                throw new IllegalStateException("Cart가 존재하지 않습니다.");
            }
        }
        else {
            throw new IllegalStateException("사용자를 찾을 수 없습니다.");
        }
    }

    public void clearCartItem(Integer userKey) {
    Optional<CustomerEntity> foundCustomerEntity = customerRepo.findByUserKey(userKey);
    if(foundCustomerEntity.isPresent())
    {
        CustomerEntity customerEntity = foundCustomerEntity.get();
        if(customerEntity.getCartEntity()==null)
        {
            throw new IllegalStateException("사용자의 카트가 비었습니다.");
        }
        else
        {
            CartEntity cartEntity = customerEntity.getCartEntity();
            for(CartItemEntity cartItemEntity : cartEntity.getCartItemEntityList())
            {
                cartItemRepo.delete(cartItemEntity);
            }
        }
    }
    }

    public void deleteCartItem(Integer cartItemKey) {
        cartItemRepo.deleteById(cartItemKey);
    }
}


package net.prescent.service;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FPOrderCustomerDto;
import net.prescent.dto.FPOrderListDto;
import net.prescent.entity.*;
import net.prescent.repository.CustomerRepository;
import net.prescent.repository.FPOrderRepository;
import net.prescent.repository.FinishedProductRepository;
import net.prescent.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FPOrderService {
    final FPOrderRepository fpOrderRepo;
    final FinishedProductRepository finishedProductRepo;
    final CustomerRepository customerRepo;
    final SellerRepository sellerRepo;
    final CartService cartService;

    public FPOrderService(FPOrderRepository fpOrderRepo, FinishedProductRepository finishedProductRepo, CustomerRepository customerRepo, SellerRepository sellerRepo, CartService cartService) {
        this.fpOrderRepo = fpOrderRepo;
        this.finishedProductRepo = finishedProductRepo;
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
        this.cartService = cartService;
    }


    public FPOrderEntity customerOrderFinishedProduct(FPOrderCustomerDto fpOrderCustomerDto)
    {

        FPOrderEntity fpOrderEntity = this.preFPOrderCustomerDtoToFPOrderEntity(fpOrderCustomerDto); //이름 어떡할지 모르곘음
        log.info("*****************************\nfpOrderEntity내부 totalPrice값: "+fpOrderEntity.getTotalPrice() );
        fpOrderRepo.save(fpOrderEntity);
        finishedProductRepo.save(fpOrderEntity.getFinishedProductEntity());
        customerRepo.save(fpOrderEntity.getCustomerEntity());

        log.debug("==================================== fporderEntity저장도 끝+++++++++"+fpOrderEntity);
        return fpOrderEntity;
        // Dto로 만들어서 보내야 하나? Entity 모든 필드 정보를 보내야 해서 Dto만들어도 entity와 같은 필드.
    }

    public FPOrderEntity preFPOrderCustomerDtoToFPOrderEntity(FPOrderCustomerDto fpOrderCustomerDto)
    {
        FPOrderEntity fpOrderEntity = new FPOrderEntity();
        log.debug("=----------------------------------------------------------------preFPOrderToEntity내부 체크 시작)"+fpOrderCustomerDto.getFpKey());
        Optional<FinishedProductEntity> finishedProductEntity = finishedProductRepo.findByFpKey(fpOrderCustomerDto.getFpKey());
        log.debug("=----------------------------------------------------------------preFPOrderToEntity내부 findByKey실행완료)");
        if(!finishedProductEntity.isPresent())
        {
            throw new IllegalStateException("주문하려고 하는 제품이 존재하지 않는 제품입니다.");
        }

        Optional<CustomerEntity> customerEntity = customerRepo.findByUserKey(fpOrderCustomerDto.getCustomerKey());
        log.debug("=----------------------------------------------------------------preFPOrderToEntity내부 findByCustomerKey실행완료)");
        if(!customerEntity.isPresent())
        {
            throw new IllegalStateException("주문하려는 고객의 정보가 존재하지 않습니다.");
        }
        log.debug("=----------------------------------------------------------------preFPOrderToEntity내부 체크까진 끝)");
        return fpOrderEntity.FPOrderCustomerDtoToFPOrderEntity(finishedProductEntity.get(), customerEntity.get(), fpOrderCustomerDto.getPurchaseInfo(), fpOrderCustomerDto.getPickupDate(), fpOrderCustomerDto.getPickupTime(),fpOrderCustomerDto.getCount());
    }

    public ArrayList<FPOrderListDto> customerFPOrderList(Integer custKey) {
        Optional<CustomerEntity> foundCustomerEntity = customerRepo.findByUserKey(custKey);
        if(foundCustomerEntity.isPresent()){
            CustomerEntity customerEntity = foundCustomerEntity.get();
            log.debug("FPOrderService내부 customerFPOrderList메서드"+customerEntity.getIdEmail());
            List<FPOrderEntity> fpOrderEntityList = customerEntity.getFpOrderEntityList();
            if(fpOrderEntityList == null)
            {
                return null;
            }
            else
            {
                List<FPOrderListDto> fpOrderListDtoArrayList = new ArrayList<>();
                for(FPOrderEntity fpOrderEntity : fpOrderEntityList)
                {
                    FinishedProductEntity finishedProductEntity = fpOrderEntity.getFinishedProductEntity();
                    if(finishedProductEntity == null)
                    {
                        throw new IllegalStateException("상품이 더이상 존재하지 않습니다.");
                    }
                    else
                    {
                        FPOrderListDto fpOrderListDto = new FPOrderListDto(fpOrderEntity, finishedProductEntity);
                        log.debug("FPOrderService내부 customerFPOrderList메서드 for문 내부"+fpOrderListDto.getFpDetail());
                        fpOrderListDtoArrayList.add(fpOrderListDto);
                    }
                }
                return (ArrayList<FPOrderListDto>) fpOrderListDtoArrayList;
            }
        }
        else {
            throw new IllegalStateException("주문목록을 조회할 고객 정보가 존재하지 않습니다.");
        }
    }

    public ArrayList<FPOrderListDto> sellerFPOrderList(Integer userKey) {
        List<FPOrderListDto> fpOrderListDtoList = new ArrayList<>();
        for(FPOrderEntity fpOrderEntity : fpOrderRepo.findAll())
        {
            if(fpOrderEntity.getFinishedProductEntity().getFlowerShopEntity().getSellerEntity().getUserKey() == userKey) {
                FPOrderListDto fpOrderListDto = new FPOrderListDto(fpOrderEntity, fpOrderEntity.getFinishedProductEntity());
                fpOrderListDto.setFlowerShopName(fpOrderEntity.getFinishedProductEntity().getFlowerShopEntity().getShopName());
                fpOrderListDtoList.add(fpOrderListDto);
            }
        // DB에 sellerKey를 포함시킬 경우
//        Optional<SellerEntity> foundSellerEntity = sellerRepo.findByUserKey(userKey);
//        if(foundSellerEntity.isPresent()) {
//            SellerEntity sellerEntity = foundSellerEntity.get();
//            log.debug("FPOrderService내부 customerFPOrderList메서드" + sellerEntity.getIdEmail());
//            List<FPOrderEntity> fpOrderEntityList = sellerEntity.getFpOrderEntityList();
//            if(fpOrderEntityList == null)
//            {
//                return null;
//            }
//            else
//            {
//                List<FPOrderListDto> fpOrderListDtoArrayList = new ArrayList<>();
//                for(FPOrderEntity fpOrderEntity : fpOrderEntityList)
//                {
//                    FinishedProductEntity finishedProductEntity = fpOrderEntity.getFinishedProductEntity();
//                    if(finishedProductEntity == null)
//                    {
//                        throw new IllegalStateException("상품이 더이상 존재하지 않습니다.");
//                    }
//                    else
//                    {
//                        FPOrderListDto fpOrderListDto = new FPOrderListDto(fpOrderEntity, finishedProductEntity);
//                        log.debug("FPOrderService내부 customerFPOrderList메서드 for문 내부"+fpOrderListDto.getFpDetail());
//                        fpOrderListDtoArrayList.add(fpOrderListDto);
//                    }
//                }
//                return (ArrayList<FPOrderListDto>) fpOrderListDtoArrayList;
//            }
//        }
        }
        return (ArrayList<FPOrderListDto>) fpOrderListDtoList;
    }

    public void customerOrderInCart(Integer userKey, String purchaseInfo) {
        Optional<CustomerEntity> foundCustomerEntity = customerRepo.findByUserKey(userKey);
        if(foundCustomerEntity.isPresent())
        {
            CustomerEntity customerEntity = foundCustomerEntity.get();
            if(customerEntity.getCartEntity() !=null)
            {
                List<CartItemEntity> cartItemEntityList = customerEntity.getCartEntity().getCartItemEntityList();
                for(CartItemEntity cartItemEntity : cartItemEntityList)
                {
                    FPOrderCustomerDto fpOrderCustomerDto = cartItemToFpOrderCustomerDto(cartItemEntity);
                    fpOrderCustomerDto.setCustomerKey(userKey);
                    fpOrderCustomerDto.setPurchaseInfo(purchaseInfo);
                    customerOrderFinishedProduct(fpOrderCustomerDto);
                }
            }
            else {
                throw new IllegalStateException("카트에 담긴 상품이 존재하지 않습니다");
            }
        }
        cartService.clearCartItem(userKey);
    }

    private FPOrderCustomerDto cartItemToFpOrderCustomerDto(CartItemEntity cartItemEntity) {
        FPOrderCustomerDto fpOrderCustomerDto = new FPOrderCustomerDto();
        fpOrderCustomerDto.setFpKey(cartItemEntity.getFinishedProductEntity().getFpKey());
        fpOrderCustomerDto.setPickupDate(cartItemEntity.getPickupDate());
        fpOrderCustomerDto.setPickupTime(cartItemEntity.getPickupTime());
        fpOrderCustomerDto.setCount(cartItemEntity.getCount());
        return fpOrderCustomerDto;
    }

    public String sellerManageOrder(Integer userKey, Integer fpOrderKey, String state) {
        Optional<FPOrderEntity> foundFpOrderEntity = fpOrderRepo.findById(fpOrderKey);
        if(foundFpOrderEntity.isPresent())
        {
            FPOrderEntity fpOrderEntity = foundFpOrderEntity.get();
            fpOrderEntity.setFpOrderState(state);
            fpOrderRepo.save(fpOrderEntity);
            return fpOrderEntity.getFpOrderState();
        }
        else {
            throw new IllegalStateException("주문을 찾을 수 없습니다.");
        }
    }
}

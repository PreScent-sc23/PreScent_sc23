package net.prescent.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.prescent.entity.FPOrderEntity;
import net.prescent.entity.FinishedProductEntity;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class FPOrderListDto {
    private String flowerShopName;
    private Integer fpOrderKey;
    private String fpOrderState;
    private String pickupDate;
    private String pickupTime;
    private String purchaseInfo;

    private Integer fpKey;
    private String fpName;
    private String fpTag;
    private String fpImage;

    private String[] fpFlowerList;

    private String fpDetail;

    private Integer count=1;
    private Integer totalPrice;

    public FPOrderListDto(FPOrderEntity fpOrderEntity, FinishedProductEntity finishedProductEntity)
    {
        this.flowerShopName = finishedProductEntity.getFlowerShopEntity().getShopName();
        this.fpOrderKey = fpOrderEntity.getFpOrderKey();
        this.fpOrderState = fpOrderEntity.getFpOrderState();
        this.pickupDate =fpOrderEntity.getPickupDate();
        this.pickupTime =fpOrderEntity.getPickupTime();
        this.purchaseInfo = fpOrderEntity.getPurchaseInfo();
        this.count = fpOrderEntity.getCount();
        this.totalPrice = fpOrderEntity.getTotalPrice();

        this.fpKey = finishedProductEntity.getFpKey();
        this.fpName = finishedProductEntity.getFpName();
        this.fpTag = finishedProductEntity.getFpTag();
        this.fpImage = finishedProductEntity.getFpImage();
        this.fpFlowerList = finishedProductEntity.getFpFlowerList();
        this.fpDetail = finishedProductEntity.getFpDetail();

    }
}

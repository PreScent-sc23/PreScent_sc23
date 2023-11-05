package Unknown.PreScent.dto;

import javax.persistence.*;

@Entity
public class FlowerShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopKey;
    @Column(nullable = false)
    private Integer sellerKey;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false)
    private String shopPhoneNum;
    @Column(nullable = false)
    private String shopLocation;
    private int[][] openingHours;
    @Column(nullable = false)
    private boolean isOpened = false;
    private String[] holiday;
    @Column(nullable = false)
    private boolean isSub = false;

    public FlowerShop(Integer shopKey, Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday, boolean isSub) {
        this.shopKey = shopKey;
        this.sellerKey = sellerKey;
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.openingHours = openingHours;
        this.isOpened = isOpened;
        this.holiday = holiday;
        this.isSub = isSub;
    }
    public FlowerShop(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday) {
        this.sellerKey = sellerKey;
        this.shopName = shopName;
        this.shopPhoneNum = shopPhoneNum;
        this.shopLocation = shopLocation;
        this.openingHours = openingHours;
        this.isOpened = isOpened;
        this.holiday = holiday;
    }

    public FlowerShop(){}
    public Integer getshopKey() {
        return shopKey;
    }

    public void setShopKey(Integer shopKey) {
        this.shopKey = shopKey;
    }

    public int getSellerKey() {
        return sellerKey;
    }

    public void setSellerKey(Integer sellerKey) {
        this.sellerKey = sellerKey;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhoneNum() {
        return shopPhoneNum;
    }

    public void setShopPhoneNum(String shopPhoneNum) {
        this.shopPhoneNum = shopPhoneNum;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public int[][] getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(int[][] openingHours) {
        this.openingHours = openingHours;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setisOpened(boolean opened) {
        isOpened = opened;
    }

    public String[] getHoliday() {
        return holiday;
    }

    public void setHoliday(String[] holiday) {
        this.holiday = holiday;
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }
}
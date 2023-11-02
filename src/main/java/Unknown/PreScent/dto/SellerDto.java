package Unknown.PreScent.dto;

public class SellerDto {

    private Long sellerKey;    //system ID
    private String sellerName;
    private String ID;
    private String password;
    private String sellerPhoneNum;

    public SellerDto() {
    }

    public Long getSellerKey() {
        return sellerKey;
    }

    public void setSellerKey(Long sellerKey) {
        this.sellerKey = sellerKey;
    }
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
    }
}

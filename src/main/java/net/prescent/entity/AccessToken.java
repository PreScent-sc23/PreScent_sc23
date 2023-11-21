package net.prescent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "access_token")
public class AccessToken {
    @Id
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_key")
    private SellerEntity seller;

    public AccessToken(String token, SellerEntity seller) {
        this.token = token;
        this.seller = seller;
    }
}

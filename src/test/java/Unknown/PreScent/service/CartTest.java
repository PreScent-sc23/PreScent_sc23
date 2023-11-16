package Unknown.PreScent.service;

import Unknown.PreScent.entity.CartEntity;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class CartTest {
    @Autowired
    private CartService cartService;

    @Test
    @DisplayName("아무 것도 안함")
    public void doNotingTest(){

    }

    @Test
    @DisplayName("장바구니 추가")
    public void addCartTest(){
        int a;
    }

    @Test
    @DisplayName("장바구니 아이템 추가")
    public void addCartItemTest(){
        int a;
    }

}

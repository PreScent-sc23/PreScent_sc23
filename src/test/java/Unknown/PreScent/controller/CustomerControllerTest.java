package Unknown.PreScent.controller;


import Unknown.PreScent.dto.CustomerDto;
import Unknown.PreScent.service.CustomerService;
import net.bytebuddy.asm.MemberAttributeExtension;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CustomerService customerService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    public CustomerDto createCustomerDto(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName("suhyeon");
        customerDto.setCustomerPhonenum("010-1111-2222");
        customerDto.setCustomerIdEmail("ajou.gmail.com");
        customerDto.setCustomerPassword("04prescent");
        return customerDto;
    }

    @Test
    @DisplayName("고객/ 초기위치 설정안된 로그인(1번째 로그인)")
    public void whenLoginWithLocationNull_thenRedirectToSetLocation() throws Exception {
        CustomerDto customerDto = createCustomerDto();
        customerDto.setCustomerLocation(null);

        when(customerService.login(anyString(), anyString())).thenReturn(customerDto);

        mockMvc.perform(post("/customer/login")
                        .param("id", "test@example.com")
                        .param("password", "password"))
                .andExpect(redirectedUrl("/customer/set-location"))
                .andExpect(request().sessionAttribute("loginCustomerIdEmail", "test@example.com"));
    }

    @Test
    @DisplayName("고객/ 초기위치 설정된 로그인(n번째 로그인)")
    public void whenLoginWithLocationNotNull_thenRedirectToMain() throws Exception {
        CustomerDto customer = new CustomerDto();
        customer.setCustomerLocation("Some location");

        when(customerService.login(anyString(), anyString())).thenReturn(customer);

        mockMvc.perform(post("/customer/login")
                        .param("id", "test@example.com")
                        .param("password", "password"))
                .andExpect(redirectedUrl("/customer/main"))
                .andExpect(request().sessionAttribute("loginCustomerIdEmail", "test@example.com"));
    }
}
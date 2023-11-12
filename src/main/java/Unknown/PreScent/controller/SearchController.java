package Unknown.PreScent.controller;


import Unknown.PreScent.dto.FinishedProductDto;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private SearchService searchService;

    @GetMapping("/finished-product")
    public String searchTag() { return "login"; }

    @PostMapping("/finished-product")
    public String searchTag(@RequestParam String fpTag, @RequestParam String sortHow,
                        HttpSession session, RedirectAttributes redirectAttributes){
        try {
            Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagAsc(fpTag, sortHow);
            session.setAttribute("tagSearchAscResult", searchResult);
            return "redirect:/finished-product";//????
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("tagSearchError", e.getMessage());
            return "redirect:/main";
        }
    }

}

package Unknown.PreScent.controller;


import Unknown.PreScent.dto.FinishedProductDto;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/finished-product")
    public String searchTag() { return "finished-product"; }

    @PostMapping("/finished-product")
    public List<FinishedProductEntity> searchTag(@RequestParam String fpTag, @RequestParam String sortHow,
                        HttpSession session, RedirectAttributes redirectAttributes){
        Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagAsc(fpTag, sortHow);
        session.setAttribute("tagSearchAscResult", searchResult);
        return searchResult.get();//????
    }

}

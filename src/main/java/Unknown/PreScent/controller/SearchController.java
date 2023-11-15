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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private SearchService searchService;

//    @GetMapping("/search")
//    public ResponseEntity<Map<List<FinishedProductEntity>, Object>> searchTag(@RequestParam String query,
//                                                                HttpServletRequest request, HttpServletResponse response){
//        System.out.println("Qurey: " + query + "----------------\n");
//
//        if(query.startsWith("#")){
//            String[] queryResult = query.split("#");
//
//            System.out.println("query split: " + queryResult[0] + "----------------\n");
//
//            Map<List<FinishedProductEntity>,Object> result = new HashMap<>();
//            Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagDefault(queryResult[0]);
////            Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
//            result.put(searchResult.get(), request.getAttribute(ERROR_EXCEPTION));
////            result.put("Message", ex.getMessage());
//
//            Integer statusCode = (Integer) request.getAttribute(ERROR_EXCEPTION);
//
//            return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
//        }
//
//        return ResponseEntity.noContent().build();
//
//    }


    @GetMapping("/search")
    public ResponseEntity<List<FinishedProductEntity>> searchTag(@RequestBody String query,
                                                                              HttpServletRequest request, HttpServletResponse response){
        System.out.println("Qurey: " + query + "----------------\n");

        if(query.startsWith("#")){
            String[] queryResult = query.split("#");

            System.out.println("query split: " + queryResult[0] + "----------------\n");

            Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagDefault(queryResult[0]);
            List<FinishedProductEntity> result = searchResult.get();

            Integer statusCode = (Integer) request.getAttribute(ERROR_EXCEPTION);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }

        return ResponseEntity.noContent().build();

    }


}

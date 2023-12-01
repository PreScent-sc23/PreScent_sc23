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
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    public ResponseEntity<List<FinishedProductDto>> searchTag(@RequestParam String query){
        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
//        System.out.println("Qurey: " + query + "----------------");
//        System.out.println("decodedQuery: " + decodedQuery + "----------------\n");

        if(decodedQuery.startsWith("#")){
            String[] queryResult = decodedQuery.split("#");

            System.out.println("query split0: " + queryResult[0] + "----------------\n");
            System.out.println("query split1: " + queryResult[1] + "----------------\n");
            queryResult[1] = "#" + queryResult[1];

            Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagDefault(queryResult[1]);
            List<FinishedProductEntity> result = searchResult.get();

            System.out.println("---------------------------------------------------------------------");
            for(FinishedProductEntity fp : result){
                System.out.println("fpName: " + fp.getFpName());
                System.out.println("fpPrice: " + fp.getFpPrice());
                System.out.println("fpTag: " + fp.getFpTag());
                System.out.println("----------------------------------");
            }
            System.out.println("---------------------------------------------------------------------");

            List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
            for(FinishedProductEntity fp : result){
                finalResult.add(FinishedProductDto.toFinishedProductDto(fp));
            }


            return ResponseEntity.ok(finalResult);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.noContent().build();

    }


}

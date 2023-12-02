package net.prescent.controller;


import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
            
            List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
            for(FinishedProductEntity fp : result){
                finalResult.add(FinishedProductDto.toFinishedProductDto2(fp));
            }

            System.out.println("---------------------------------====================================");
            for(FinishedProductDto fp : finalResult){
                System.out.println("fpKey: " + fp.getFpKey());
                System.out.println("fpName: " + fp.getFpName());
                System.out.println("fpPrice: " + fp.getFpPrice());
                System.out.println("fpTag: " + fp.getFpTag());
                System.out.println("shopKey: " + fp.getShopKey());
                System.out.println("fpImage: " + fp.getFpImage());
                System.out.println("----------------------------------");
            }
            System.out.println("---------------------------------====================================");

            return ResponseEntity.ok(finalResult);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.noContent().build();

    }


    @GetMapping("/detail/{fpKey}")
    public ResponseEntity<FinishedProductDto> showDetail(@PathVariable String fpKey){
        System.out.println("received fpKey:" + fpKey + "==================");

        Integer intFpKey = Integer.parseInt(fpKey);

        Optional<FinishedProductEntity> searchResult = searchService.searchByFpKey(intFpKey);

        if(searchResult.isPresent()) {
            FinishedProductEntity result = searchResult.get();

            FinishedProductDto finalResult = FinishedProductDto.toFinishedProductDto(result);

            System.out.println("---------------------------------====================================");
            System.out.println("fpKey: " + finalResult.getFpKey());
            System.out.println("fpName: " + finalResult.getFpName());
            System.out.println("fpPrice: " + finalResult.getFpPrice());
            System.out.println("fpTag: " + finalResult.getFpTag());
            System.out.println("shopKey: " + finalResult.getShopKey());
            System.out.println("fpDetail: " + finalResult.getFpDetail());
            String[] arr = finalResult.getFpFlowerList();
            System.out.println("fpFlowerList: " + arr[0]);
            System.out.println("---------------------------------====================================");

            return ResponseEntity.ok(finalResult);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.noContent().build();
    }
}

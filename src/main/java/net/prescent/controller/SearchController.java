package net.prescent.controller;


import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<FinishedProductDto>> searchTag(@RequestHeader String Authorization, @RequestParam String query){
        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
        String token = Authorization.substring(7);

        if(decodedQuery.startsWith("#")){
            List<FinishedProductDto> finalResult = searchService.returnSearchByTag(token,decodedQuery);

            if(finalResult == null) return ResponseEntity.noContent().build();

            return ResponseEntity.ok(finalResult);
        }
        else
        {
            List<FinishedProductDto> finalResult = searchService.returnSearchByFlower(token,decodedQuery);

            if(finalResult == null) return ResponseEntity.noContent().build();

            return ResponseEntity.ok(finalResult);

        }

    }

    @GetMapping("/detail/{fpKey}")
    public ResponseEntity<FinishedProductDto> showDetail(@PathVariable String fpKey){
        System.out.println("received fpKey:" + fpKey + "==================");

        Integer intFpKey = Integer.parseInt(fpKey);

        Optional<FinishedProductEntity> searchResult = searchService.searchByFpKey(intFpKey);

        if(searchResult.isPresent()) {
            FinishedProductEntity result = searchResult.get();

            FinishedProductDto finalResult = FinishedProductDto.toFinishedProductDto(result);

            printResult(finalResult);

            return ResponseEntity.ok(finalResult);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.noContent().build();
    }


    private static void printManyResult(List<FinishedProductDto> finalResult) {
        System.out.println("---------------------------------====================================");
        for(FinishedProductDto fp : finalResult){
            System.out.println("fpKey: " + fp.getFpKey());
            System.out.println("fpName: " + fp.getFpName());
            System.out.println("fpPrice: " + fp.getFpPrice());
            System.out.println("fpTag: " + fp.getFpTag());
            System.out.println("shopKey: " + fp.getShopKey());
            System.out.println("----------------------------------");
        }
        System.out.println("---------------------------------====================================");
    }


    private static void printResult(FinishedProductDto finalResult) {
        System.out.println("---------------------------------====================================");
        System.out.println("fpKey: " + finalResult.getFpKey());
        System.out.println("fpName: " + finalResult.getFpName());
        System.out.println("fpPrice: " + finalResult.getFpPrice());
        System.out.println("fpTag: " + finalResult.getFpTag());
        System.out.println("shopKey: " + finalResult.getShopKey());
        System.out.println("fpDetail: " + finalResult.getFpDetail());
        System.out.println("---------------------------------====================================");
    }


}

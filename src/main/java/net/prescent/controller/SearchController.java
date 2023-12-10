package net.prescent.controller;


import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
            List<FinishedProductDto> finalResult = searchService.searchByFlower(token,decodedQuery);

            if(finalResult == null) return ResponseEntity.noContent().build();

            return ResponseEntity.ok(finalResult);

        }

    }

    @GetMapping("/detail/{fpKey}")
    public ResponseEntity<FinishedProductDto> showDetail(@PathVariable String fpKey){
        log.info("received fpKey:" + fpKey + "==================");

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
        log.info("---------------------------------====================================");
        for(FinishedProductDto fp : finalResult){
            log.info("fpKey: " + fp.getFpKey());
            log.info("fpName: " + fp.getFpName());
            log.info("fpPrice: " + fp.getFpPrice());
            log.info("fpTag: " + fp.getFpTag());
            log.info("shopKey: " + fp.getShopKey());
            log.info("----------------------------------");
        }
        log.info("---------------------------------====================================");
    }


    private static void printResult(FinishedProductDto finalResult) {
        log.info("---------------------------------====================================");
        log.info("fpKey: " + finalResult.getFpKey());
        log.info("fpName: " + finalResult.getFpName());
        log.info("fpPrice: " + finalResult.getFpPrice());
        log.info("fpTag: " + finalResult.getFpTag());
        log.info("shopKey: " + finalResult.getShopKey());
        log.info("fpDetail: " + finalResult.getFpDetail());
        log.info("---------------------------------====================================");
    }


}

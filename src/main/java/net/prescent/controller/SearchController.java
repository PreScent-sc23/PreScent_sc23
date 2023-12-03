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

        Integer intFpKey = Integer.parseInt(fpKey);

        Optional<FinishedProductEntity> searchResult = searchService.searchByFpKey(intFpKey);

        if(searchResult.isPresent()) {
            FinishedProductEntity result = searchResult.get();

            FinishedProductDto finalResult = FinishedProductDto.toFinishedProductDto(result);

            String[] arr = finalResult.getFpFlowerList();
            String[] arr2 = arr[0].split(" ");
            finalResult.setFpFlowerList(arr2);

            System.out.println("---------------------------------====================================");
            System.out.println("fpKey: " + finalResult.getFpKey());
            System.out.println("fpName: " + finalResult.getFpName());
            System.out.println("fpPrice: " + finalResult.getFpPrice());
            System.out.println("fpTag: " + finalResult.getFpTag());
            System.out.println("fpDetail: " + finalResult.getFpDetail());
            System.out.println("fpImage: " + finalResult.getFpImage());

            System.out.println("fpFlowerList1: " + arr2[0]);
            System.out.println("fpFlowerList2: " + arr2[1]);
            System.out.println("fpFlowerList3: " + arr2[2]);
            System.out.println("---------------------------------====================================");

            return ResponseEntity.ok(finalResult);
//            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.noContent().build();
    }
}

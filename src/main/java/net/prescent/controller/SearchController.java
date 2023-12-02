package net.prescent.controller;


import lombok.extern.slf4j.Slf4j;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private SearchService searchService;

//    @GetMapping("/search")
//    public ResponseEntity<Map<List<FinishedProductEntity>, Object>> searchTag(@RequestParam String query,
//                                                                HttpServletRequest request, HttpServletResponse response){
//        log.debug("Qurey: " + query + "----------------\n");
//
//        if(query.startsWith("#")){
//            String[] queryResult = query.split("#");
//
//            log.debug("query split: " + queryResult[0] + "----------------\n");
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
    public ResponseEntity<List<FinishedProductEntity>> searchTag(@RequestParam String query,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response){
        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
//        log.debug("Qurey: " + query + "----------------");
//        log.debug("decodedQuery: " + decodedQuery + "----------------\n");

        if(decodedQuery.startsWith("#")){
            String[] queryResult = decodedQuery.split("#");

            log.debug("query split0: " + queryResult[0] + "----------------\n");
            log.debug("query split1: " + queryResult[1] + "----------------\n");

            Optional<List<FinishedProductEntity>> searchResult = searchService.searchByTagDefault(queryResult[1]);
            List<FinishedProductEntity> result = searchResult.get();

            log.debug("---------------------------------------------------------------------");
            for(FinishedProductEntity fp : result){
                log.debug("fpName: " + fp.getFpName());
                log.debug("fpPrice: " + fp.getFpPrice());
                log.debug("fpTag: " + fp.getFpTag());
                log.debug("----------------------------------");
            }
            log.debug("---------------------------------------------------------------------");

            Integer statusCode = (Integer) request.getAttribute(ERROR_EXCEPTION);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }

        return ResponseEntity.noContent().build();

    }


}

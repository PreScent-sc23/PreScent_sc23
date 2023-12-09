package net.prescent.service;

import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.repository.FinishedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final FinishedProductRepository finishedProductRepository;



    public Page<Optional<FinishedProductEntity>> searchByTag(String fpTag, String sortHow, Integer pageNum){
        return finishedProductRepository.findByFpTagContaining(fpTag, PageRequest.of(pageNum, 5, Sort.by(Sort.Order.asc(sortHow))));
    }

    public Optional<List<FinishedProductEntity>> searchByTagDefault(String fpTag){
        return finishedProductRepository.findByFpTagContaining(fpTag, Sort.by(Sort.Order.asc("fpName")));
    }

    public Optional<FinishedProductEntity> searchByFpKey(Integer fpKey){
        return finishedProductRepository.findByFpKey(fpKey, Sort.by(Sort.Order.asc("fpName")));
    }

    public Optional<List<FinishedProductEntity>> searchByTagAsc(String fpTag, String sortHow){
        return finishedProductRepository.findByFpTagContaining(fpTag, Sort.by(Sort.Order.asc(sortHow)));
    }

    public Optional<List<FinishedProductEntity>> searchByTagDesc(String fpTag, String sortHow){
        return finishedProductRepository.findByFpTagContaining(fpTag, Sort.by(Sort.Order.desc(sortHow)));
    }

    public List<FinishedProductDto> returnSearchByTag(String decodedQuery) {

        String[] queryResult = decodedQuery.split("#");

        System.out.println("query split0: " + queryResult[0] + "----------------\n");
        System.out.println("query split1: " + queryResult[1] + "----------------\n");
        queryResult[1] = "#" + queryResult[1];

        Optional<List<FinishedProductEntity>> searchResult = searchByTagDefault(queryResult[1]);
        List<FinishedProductEntity> result = searchResult.get();

        List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
        for(FinishedProductEntity fp : result){
            finalResult.add(FinishedProductDto.toFinishedProductDto2(fp));
        }
        return finalResult;
    }

    public List<FinishedProductDto> returnSearchByFPName(String decodedQuery) {
        Optional<List<FinishedProductEntity>> searchResult = finishedProductRepository.findByFpNameContaining(decodedQuery);
        List<FinishedProductEntity> result = searchResult.get();

        List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
        for(FinishedProductEntity fp : result){
            finalResult.add(FinishedProductDto.toFinishedProductDto2(fp));
        }
        return finalResult;
    }
}

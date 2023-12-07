package net.prescent.service;

import net.prescent.entity.FinishedProductEntity;
import net.prescent.repository.FinishedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}

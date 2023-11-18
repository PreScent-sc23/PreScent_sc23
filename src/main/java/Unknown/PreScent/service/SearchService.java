package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.repository.FinishedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Optional<List<FinishedProductEntity>> searchByTagAsc(String fpTag, String sortHow){
        return finishedProductRepository.findByFpTagContaining(fpTag, Sort.by(Sort.Order.asc(sortHow)));
    }

    public Optional<List<FinishedProductEntity>> searchByTagDesc(String fpTag, String sortHow){
        return finishedProductRepository.findByFpTagContaining(fpTag, Sort.by(Sort.Order.desc(sortHow)));
    }

}

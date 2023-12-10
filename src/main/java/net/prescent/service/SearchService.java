package net.prescent.service;

import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.CustomerEntity;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.repository.FinishedProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final FinishedProductRepository finishedProductRepository;
    private final AccessTokenService accessTokenService;

    Double MAX_DISTANCE = 3000.0;
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

    public List<FinishedProductDto> returnSearchByTag(String token, String decodedQuery) {
        CustomerEntity customerEntity = accessTokenService.getCustomerFromToken(token);

        String[] queryResult = decodedQuery.split("#");

        log.info("query split0: " + queryResult[0] + "----------------\n");
        log.info("query split1: " + queryResult[1] + "----------------\n");
        queryResult[1] = "#" + queryResult[1];

        Optional<List<FinishedProductEntity>> searchResult = searchByTagDefault(queryResult[1]);
        List<FinishedProductEntity> result = searchResult.get();

        List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
        for(FinishedProductEntity fp : result){
            Double distance = calculateDistance(customerEntity.getLatitude(), customerEntity.getLongitude(), fp.getFlowerShopEntity().getLatitude(), fp.getFlowerShopEntity().getLongitude());
            if(distance<= MAX_DISTANCE) {
                finalResult.add(FinishedProductDto.toFinishedProductDto(fp));
            }
        }
        return finalResult;
    }
    public List<FinishedProductDto> searchByFlower(String token, String decodedQuery) {
        CustomerEntity customerEntity = accessTokenService.getCustomerFromToken(token);
        List<FinishedProductEntity> Result = finishedProductRepository.findAll();


        List<FinishedProductDto> finalResult = new ArrayList<FinishedProductDto>();
        for(FinishedProductEntity fp : Result){
            List<String> fpFlowerList = new ArrayList<>(Arrays.asList(fp.getFpFlowerList()));
            Double distance = calculateDistance(customerEntity.getLatitude(), customerEntity.getLongitude(), fp.getFlowerShopEntity().getLatitude(), fp.getFlowerShopEntity().getLongitude());
            if(distance<= MAX_DISTANCE && fpFlowerList.contains(decodedQuery)) {
                finalResult.add(FinishedProductDto.toFinishedProductDto(fp));
            }
        }
        return finalResult;
    }
    public static double deg2rad(double angle) {
        return angle * Math.PI / 180;
    }
    public static double rad2deg(double rad){
        return rad*180/Math.PI;
    }
    private Double calculateDistance(Double lat1, Double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
}

package Unknown.PreScent.repository;

import Unknown.PreScent.dto.SellerDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBSellerRepository implements SellerRepository{
    @Override
    public List<SellerDto> findAll() {
        return null;
    }

    @Override
    public List<SellerDto> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<SellerDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<SellerDto> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(SellerDto entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends SellerDto> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends SellerDto> S save(S entity) {
        return null;
    }

    @Override
    public <S extends SellerDto> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SellerDto> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends SellerDto> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends SellerDto> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<SellerDto> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SellerDto getOne(String s) {
        return null;
    }

    @Override
    public SellerDto getById(String s) {
        return null;
    }

    @Override
    public SellerDto getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends SellerDto> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SellerDto> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends SellerDto> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends SellerDto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SellerDto> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SellerDto> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends SellerDto, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }






/*
    @Override
    public SellerDto save(SellerDto seller) {

        return null;
    }

 */

    @Override
    public Optional<SellerDto> findBySellerKey(Long sellerKey) {
        return Optional.empty();
    }

    @Override
    public Optional<SellerDto> findBySellerName(String sellerName) {
        return Optional.empty();
    }

    @Override
    public Optional<SellerDto> findByID(String ID) {
        return Optional.empty();
    }

    @Override
    public Optional<SellerDto> findByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public Optional<SellerDto> findBySellerPhoneNum(String sellerPhoneNum) {
        return Optional.empty();
    }
/*
    @Override
    public List<SellerDto> findAll() {
        return null;
    }


 */

}

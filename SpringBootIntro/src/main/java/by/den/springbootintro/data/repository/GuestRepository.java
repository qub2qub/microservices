package by.den.springbootintro.data.repository;

import by.den.springbootintro.data.entity.Guest;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

    // ???
    List<Guest> findGuestByFirstNameIsLike(String firstName, Pageable pageable);

}
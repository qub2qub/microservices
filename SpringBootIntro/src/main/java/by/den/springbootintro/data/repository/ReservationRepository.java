package by.den.springbootintro.data.repository;

import by.den.springbootintro.data.entity.Reservation;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByDate(Date date);
}
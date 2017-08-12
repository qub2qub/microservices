package by.den.springbootintro.web.service;

import by.den.springbootintro.business.domain.RoomReservation;
import by.den.springbootintro.business.service.ReservationService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationServiceRestController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET, value = "/{date}")
    public List<RoomReservation> getAllReservationsForDate(
        @PathVariable(value = "date", required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reservationService.getRoomReservationsForDate(date);
    }
}

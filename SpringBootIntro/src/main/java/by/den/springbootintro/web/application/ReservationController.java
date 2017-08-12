package by.den.springbootintro.web.application;

import by.den.springbootintro.business.service.ReservationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    // т.к. Controller должен соответствовать JavaBean спецификации
    // и иметь дефолтный конструктор без аргументов
    // поэтому сетаем через филд. (?? так ли это ??)
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(Model model,
        @RequestParam(value = "date", required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        model.addAttribute("roomReservations",
            reservationService.getRoomReservationsForDate((date == null ? new Date() : date)));
        return "reservations";
    }

    @RequestMapping(path = "/{date}", method = RequestMethod.GET)
    public String getReservations1(Model model,
        @PathVariable(value = "date", required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        model.addAttribute("roomReservations",
            reservationService.getRoomReservationsForDate((date == null ? new Date() : date)));
        return "reservations1";
    }
}

package by.den.springbootintro.web.service;

import by.den.springbootintro.business.service.GuestService;
import by.den.springbootintro.data.entity.Guest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by den on 2017-08-12
 */
@RestController
@RequestMapping(value = "/api/guests")
public class GuestServiceRestController {

    @Autowired
    GuestService guestService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Guest> getAllGuests() {
        return guestService.findAllGuests();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id:\\d+}")
    public Guest getGuestById(@PathVariable(value = "id", required = true) long guestId) {
        return guestService.getGuestById(guestId);
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public List<Guest> findGuestByFirstName(
        @PathVariable(value = "name", required = false) String name,
        Pageable pageable) {
        return guestService.findGuestByFirstNameIsLike(name, pageable);
    }*/
}

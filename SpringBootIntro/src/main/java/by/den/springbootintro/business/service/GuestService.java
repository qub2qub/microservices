package by.den.springbootintro.business.service;

import by.den.springbootintro.data.entity.Guest;
import by.den.springbootintro.data.repository.GuestRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by den on 2017-08-12
 */
@Service
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest getGuestById(long id) {
        return guestRepository.findOne(id);
    }

    public List<Guest> findAllGuests() {
        List<Guest> guests = new ArrayList<>();
        guestRepository.findAll().forEach(guests::add);
        return guests;
    }

    public Page<Guest> findAllGuestsPage(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }

    public List<Guest> findGuestByFirstNameIsLike(String firstName, Pageable pageable) {
        // ???
        return guestRepository.findGuestByFirstNameIsLike(firstName, pageable);
    }

}

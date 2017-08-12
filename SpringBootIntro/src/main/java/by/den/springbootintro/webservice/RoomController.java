package by.den.springbootintro.webservice;

import by.den.springbootintro.data.entity.Room;
import by.den.springbootintro.data.repository.RoomRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    private final RoomRepository repository;

    @Autowired
    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    List<Room> findAll(@RequestParam(required = false) String roomNumber) {

        List<Room> rooms = new ArrayList<>();
        if (roomNumber == null) {
            repository.findAll().forEach(rooms::add);
        } else {
            Optional.ofNullable(repository.findByNumber(roomNumber)).ifPresent(rooms::add);
        }
        System.out.println("roomNumber = [" + roomNumber + "], rooms.size() = [" + rooms.size()+ "]");
        return rooms;
    }
}

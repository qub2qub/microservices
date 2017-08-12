package by.den.springbootintro.webservice;

import by.den.springbootintro.data.entity.Room;
import by.den.springbootintro.data.repository.RoomRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
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
        System.out.println("roomNumber = " + roomNumber);

        if (null == roomNumber) {
//            Iterable<Room> results = this.repository.findAll();
//            results.forEach(room -> {
//                System.out.println("all rooms = " + room);
//            });
            repository.findAll().forEach(rooms::add);
        } else {
//            Room room = this.repository.findByNumber(roomNumber);
//            if (null != room) {
//                System.out.println("1 room = " + room);
//            }
            Optional.ofNullable(repository.findByNumber(roomNumber)).ifPresent(rooms::add);
        }
        System.out.println("rooms.size() = " + rooms.size());
        return rooms;
    }
}

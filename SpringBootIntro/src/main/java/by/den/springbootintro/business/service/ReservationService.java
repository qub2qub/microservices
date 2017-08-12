package by.den.springbootintro.business.service;

import by.den.springbootintro.business.domain.RoomReservation;
import by.den.springbootintro.data.entity.Guest;
import by.den.springbootintro.data.repository.GuestRepository;
import by.den.springbootintro.data.repository.ReservationRepository;
import by.den.springbootintro.data.repository.RoomRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
        ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        // Add all rooms to "roomReservationMap"
        roomRepository.findAll().forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        // Find all res-s by date and guest for each res
        Optional.ofNullable(reservationRepository.findByDate(new java.sql.Date(date.getTime())))
            .ifPresent(reservations -> reservations.forEach(reservation -> {
                Guest guest = this.guestRepository.findOne(reservation.getGuestId());
                if (guest != null) {
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            }));

        roomReservationMap.forEach( (k,v) -> {
            System.out.println(k+" \t= "+v);
        });

        return new ArrayList<>(roomReservationMap.values());
    }
}

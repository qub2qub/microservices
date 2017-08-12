package by.den.springbootintro.business.service;

import by.den.springbootintro.business.domain.RoomReservation;
import by.den.springbootintro.data.entity.Guest;
import by.den.springbootintro.data.entity.Reservation;
import by.den.springbootintro.data.entity.Room;
import by.den.springbootintro.data.repository.GuestRepository;
import by.den.springbootintro.data.repository.ReservationRepository;
import by.den.springbootintro.data.repository.RoomRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<RoomReservation> getRoomReservationsForDate(LocalDate date) {
        // БУДЕМ хранить всю инфу о комнате
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        if (date != null) {

            Iterable<Room> allHotelRooms = roomRepository.findAll();
            // Add all rooms to "roomReservationMap"
            allHotelRooms.forEach(room -> {
                RoomReservation roomReservation = new RoomReservation();
                roomReservation.setRoomId(room.getId());
                roomReservation.setRoomName(room.getName());
                roomReservation.setRoomNumber(room.getNumber());
                roomReservationMap.put(room.getId(), roomReservation);
            });

            // Нашли все комнаты, которые зарезервированы на определённую дату
            List<Reservation> reservedRoomsForDate =
                reservationRepository.findByDate(Date.valueOf(date));
            // и теперь добавляем в списке всех комнал инфу по клиентам на данную дату.
            Optional.ofNullable(reservedRoomsForDate)
                .ifPresent(reservations -> reservations.forEach(reservation -> {
                    // должны будет добавить данные для этой комнаты (из уже полученного списка)
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    // достаём инфу по гостю (на данную дату на данную комнату)
                    Guest guest = this.guestRepository.findOne(reservation.getGuestId());
                    if (guest != null) {
                        roomReservation.setFirstName(guest.getFirstName());
                        roomReservation.setLastName(guest.getLastName());
                        roomReservation.setGuestId(guest.getId());
                    }
                }));

            System.out.println("ReservationService.getRoomReservationsForDate()\n"
                + " -> DATE = " + date
                + " -> RESERVED = " + reservedRoomsForDate.size());
        }
        return new ArrayList<>(roomReservationMap.values());
    }
}

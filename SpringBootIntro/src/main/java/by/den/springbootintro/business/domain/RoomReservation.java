package by.den.springbootintro.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Собирает данные из ТРЁХ классов: ROOM and GUEST and RESERVATION,
 * чтобы представить единое бизнес ентити
 */
public class RoomReservation {

    private long roomId;
    private long guestId;
    private String roomName;
    private String roomNumber;
    private String firstName;
    private String lastName;

    // перед этим надо задать в application.properties
    // spring.jackson.serialization.write_dates_as_timestamps=false
    // тогда формат ниже можно не задавать
     //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

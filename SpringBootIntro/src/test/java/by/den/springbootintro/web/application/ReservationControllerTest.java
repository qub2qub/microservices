package by.den.springbootintro.web.application;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.den.springbootintro.business.domain.RoomReservation;
import by.den.springbootintro.business.service.ReservationService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.jni.Local;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    private static final String TEST_DATE = "2017-01-01";
    private static LocalDate date = LocalDate.of(2017, 1, 1);
    private static List<RoomReservation> resultRooms;

    @MockBean
    private ReservationService reservationService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void initClass() throws Exception {
        resultRooms = createTestReservations(date);
    }

    @Test
    public void getParamReservations() throws Exception {
        given(reservationService.getRoomReservationsForDate(date)).willReturn(resultRooms);
        this.mockMvc.perform(get("/reservations?date=" + TEST_DATE))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Test, JUnit")));
    }

    @Test
    public void getPathReservations() throws Exception {
        given(reservationService.getRoomReservationsForDate(date)).willReturn(resultRooms);
        this.mockMvc.perform(get("/reservations/" + TEST_DATE))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("JUnit, Test")));
    }

    private static List<RoomReservation> createTestReservations(LocalDate date) {
        List<RoomReservation> mockReservationList = new ArrayList<>();
        RoomReservation mockRoomReservation = new RoomReservation();
        mockRoomReservation.setLastName("Test");
        mockRoomReservation.setFirstName("JUnit");
        mockRoomReservation.setDate(date);
        mockRoomReservation.setGuestId(1);
        mockRoomReservation.setRoomNumber("J1");
        mockRoomReservation.setRoomId(100);
        mockRoomReservation.setRoomName("JUnit Room");
        mockReservationList.add(mockRoomReservation);
        return mockReservationList;
    }
}

package AirportTask;

import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AirportTask {
    public static void main(String[] args) {
        Date fromTime = new Date();
        LocalDateTime localStartDate = fromTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusHours(2);
        Date tillTime = Date.from(localStartDate.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(tillTime);


        Airport airport = Airport.getInstance();

        airport.getTerminals()
                .stream().flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> (flight.getDate().before(tillTime) && flight.getDate().after(fromTime)))
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .forEach(System.out::println);


//       airport.getTerminals().forEach(t->t.getFlights().stream().filter(flight -> flight.getDate().before(date)).filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE)).collect(Collectors.toMap(Flight::getDate,Flight::getAircraft)));
//        airport.getTerminals().stream().flatMap(terminal -> terminal.getFlights().stream()).filter(flight -> flight.getDate().before(date)).forEach(System.out::println);  // - первоначальный вариант, как дошел до него -непонятно )
//          airport.getTerminals().stream().map(terminal -> terminal.getFlights().stream().filter(flight -> flight.getDate().before(date))).forEach(System.out::println);
//        airport.getTerminals().stream().map(terminal -> terminal.getFlights().stream().filter(flight -> flight.getDate().getHours()<date.getHours()+2)).forEach(System.out::println); //- подсмотрел, но не понял почему все так же не работает
    }
}

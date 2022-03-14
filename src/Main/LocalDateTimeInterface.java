package Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Interface used for Lambda expression
 */
public interface LocalDateTimeInterface {
    LocalDateTime convertLocalDateTime(LocalTime t, LocalDate d);
}

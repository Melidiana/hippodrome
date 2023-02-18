import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void testNullException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testEmptyException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Лавка", i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testHorseMoveInvoked50Times() {
        Horse horse = mock(Horse.class);
        List<Horse> horses = Collections.nCopies(50, horse);
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        verify(horse, times(50)).move();
    }

    @Test
    public void testGetWinner() {
        Horse h1 = new Horse("Цезарь", 30, 100);
        Horse h2 = new Horse("Стрела", 25, 80);
        Horse h3 = new Horse("Сивка", 27, 90);
        List<Horse> horses = new ArrayList<>();
        horses.add(h1);
        horses.add(h2);
        horses.add(h3);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals("Цезарь", hippodrome.getWinner().getName());
    }
}

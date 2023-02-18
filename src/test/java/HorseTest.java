import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HorseTest {
    private static MockedStatic<Horse> mockedStaticHorse;
    @BeforeAll
    static void setup() {
        mockedStaticHorse = mockStatic(Horse.class);
    }
    @Test
    public void testNullException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t\t", "\n\n\n"})
    void testException(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(argument, 1.0, 2.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testNegativeSecondNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse("Бумер", -1.0, 2.0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testNegativeThirdNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse("Бумер", 1.0, -2.0)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testHorseArguments() {
        Horse twoArgsHorse = new Horse("Плотва", 30);
        Horse threeArgsHorse = new Horse("Плотва", 30, 150);
        assertEquals("Плотва", twoArgsHorse.getName());
        assertEquals(30, twoArgsHorse.getSpeed());
        assertEquals(0, twoArgsHorse.getDistance());
        assertEquals(150, threeArgsHorse.getDistance());
    }

    @Test
    public void testMoveInvokeGetRandom() {
        Horse horse = new Horse("Бумер", 30, 150);
        horse.move();
        mockedStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @Test
    public void testDistance() {
            mockedStaticHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("Сивка", 12);
            horse.move();
            assertEquals(6, horse.getDistance());

    }
}


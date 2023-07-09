import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class HippodromeTest {
    @Test
    public void getArgException_IfParamIsNull(){

        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
    }

    @Test
    public void getArgExceptionDescription_IfParamIsNull(){
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
        String expected = "Horses cannot be null.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void getArgException_IfListIsEmpty(){
        List<Horse> horses = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
    }

    @Test
    public void getArgExceptionDescription_IfListIsEmpty(){
        List<Horse> horses = new ArrayList<>();

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
        String expected = "Horses cannot be empty.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void getHorsesReturnSameList(){
        List<Horse> originalHorses = createHorsesList();
        Hippodrome hippodrome = new Hippodrome(originalHorses);
        List<Horse> retrievedHorses = hippodrome.getHorses();

        assertIterableEquals(originalHorses, retrievedHorses);

    }
    private List<Horse> createHorsesList(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, 1));
        }
        return horses;
    }


    @Test
    public void testMoveCallsMoveOnAllHorses() {
        List<Horse> mockedHorses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Horse mockedHorse = mock(Horse.class);
            mockedHorses.add(mockedHorse);
        }

        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();

        for (Horse mockedHorse : mockedHorses) {
            verify(mockedHorse, times(1)).move();
        }
    }

    @Test
    public void getWinnerWithTheBiggestDistance(){
        Horse horse1 = mock(Horse.class);
        Horse horse2 = mock(Horse.class);
        Horse horse3 = mock(Horse.class);

        when(horse1.getDistance()).thenReturn(100.0);
        when(horse2.getDistance()).thenReturn(150.0);
        when(horse3.getDistance()).thenReturn(200.0);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(horse3, winner);

    }

}
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;



class HorseTest {
    //Constructor Tests

    @Test
    public void getArgException_IfNameIsNull(){

        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 1, 1));
    }

    @Test
    public void getArgExceptionDescription_IfNameIsNull(){
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 1, 1));
        String expected = "Name cannot be null.";
        assertEquals(expected, actual.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void getArgException_IfNameHasWhitespaceCharacters(String space){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(space, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void getArgExceptionDescription_IfNameHasWhitespaceCharacters(String space){
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Horse(space, 1, 1));
        String expected = "Name cannot be blank.";
        assertEquals(expected, actual.getMessage());

    }

    @Test
    public void getArgException_IfSpeedIsNegative(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("Test", -212, 1));
    }

    @Test
    public void getArgExceptionDescription_IfSpeedIsNegative(){
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Test", -212, 1));

        String expected  = "Speed cannot be negative.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void getArgExceptionDescription_IfDistanceIsNegative(){
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Test", 21, -21));

        String expected  = "Distance cannot be negative.";
        assertEquals(expected, actual.getMessage());
    }

    //Getters Tests

    @Test
    public void getName(){
        String expected = "TestHorse";
        Horse horse = new Horse(expected, 1, 1);
        assertEquals(expected, horse.getName());
    }

    @Test
    public void getSpeed(){
        Double expected = 100.0;
        Horse horse = new Horse("TestHorse", expected, 1);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    public void getDistance(){
        Double expected = 100.0;
        Horse horse = new Horse("TestHorse", 1, expected);
        assertEquals(expected, horse.getDistance());
    }

    @Test
    public void getDistance_ShouldZero_IfConstructorHasTwoParam(){
        Double expected = 0.0;
        Horse horse = new Horse("TestHorse", 1);
        assertEquals(expected, horse.getDistance());
    }

    //test with errors

    @Test
    public void testMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("TestHorse", 1, 1);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @Test
    public void testMoveAssignsDistanceCorrectly() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double speed = 10.0;
            double initialDistance = 0.0;
            double randomDouble = 0.5;

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            Horse horse = new Horse("TestHorse", speed, initialDistance);
            horse.move();

            double expectedDistance = initialDistance + speed * randomDouble;
            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
        }
    }












}
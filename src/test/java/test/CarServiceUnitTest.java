package test;

import com.revature.model.Car;
import com.revature.model.Make;
import com.revature.model.Model;
import com.revature.model.Color;
import com.revature.model.Availability;
import com.revature.service.CarService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CarServiceUnitTest {

    CarService mockedService = Mockito.mock(CarService.class);
    Car car = new Car(1,  Color.GREEN, Make.FORD, Model.F_150, Availability.AVAILABLE);

    @Test
    public void whenGivenCarObjectCreateDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createNewCar(car));
    }

    @Test
    public void whenGivenCarObjectCreateReturnsTrue() {
        Mockito.when(mockedService.createNewCar(car)).thenReturn(car);
        Car result = mockedService.createNewCar(car);
        Assertions.assertEquals(car, result);
    }
}
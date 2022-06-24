package test;

import com.revature.model.*;
import com.revature.service.CarService;
import com.revature.service.OfferService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OfferServiceUnitTest {
    OfferService mockedService = Mockito.mock(OfferService.class);
    Offer offer = new Offer(1, 1, 1, Status.OPEN);

    @Test
    public void whenGivenOfferObjectCreateDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createNewOffer(offer));
    }

    @Test
    public void whenGivenOfferObjectCreateReturnsTrue() {
        Mockito.when(mockedService.createNewOffer(offer)).thenReturn(offer);
        Offer result = mockedService.createNewOffer(offer);
        Assertions.assertEquals(offer, result);
    }
}

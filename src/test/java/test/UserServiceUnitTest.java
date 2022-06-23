package test;

import com.revature.model.User;
import com.revature.model.Role;
import com.revature.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceUnitTest {

    UserService mockedService = Mockito.mock(UserService.class);
    User user = new User(1, "Test", "Test", "test", "testy", Role.CUSTOMER);

    @Test
    public void whenGivenUserObjectCreateDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createUser(user));
    }

    @Test
    public void whenGivenUserObjectCreateReturnsTrue() {
        Mockito.when(mockedService.createUser(user)).thenReturn(user);
        User result = mockedService.createUser(user);
        Assertions.assertEquals(user, result);
    }
}
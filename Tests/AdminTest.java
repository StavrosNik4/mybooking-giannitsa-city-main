import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest{
    Platform platform;
    Admin admin;
    Customer customer;

    @Before
    public void setUp() {
        platform = new Platform();
        customer = new Customer("customer", "123");
        admin = new Admin("test", "testy");
        platform.addUser(admin);
        platform.addUser(customer);
    }

    @Test
    public void sendMessage(){
        assertTrue(admin.sendMessage(platform, customer.getNickname(), "hello!"));
    }
}
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    Customer test;

    @Before
    public void setTest(){
        test = new Customer("cus", "123");
        Date date = Calendar.getInstance().getTime();
        Message message = new Message("sender", test.getNickname(), "hello!", date);
        test.addMessage(message);
        test.setAddress("Giannarou 6");
        test.setEmail("stavniko@csd.auth.gr");
    }

    @Test
    public void getNickname() {
        assertEquals("cus", test.getNickname());
    }

    @Test
    public void getPassword() {
        assertEquals("123", test.getPassword());
    }

    @Test
    public void getRank() {
        assertEquals("Customer", test.getRank());
    }

    @Test
    public void getMessages() {
        assertEquals("sender", test.getMessages().get(0).getSender());
        assertEquals("cus", test.getMessages().get(0).getRecipient());
        assertEquals("hello!", test.getMessages().get(0).getContent());
    }

    @Test
    public void addMessage() {
        assertEquals(1, test.getMessages().size());
        Date date = Calendar.getInstance().getTime();
        Message message = new Message("sender", test.getNickname(), "hello again!", date);
        test.addMessage(message);
        assertEquals(2, test.getMessages().size());
    }

    @Test
    public void setPassword() {
        assertEquals("123", test.getPassword());
        test.setPassword("456");
        assertEquals("456", test.getPassword());
    }

    @Test
    public void getEmail() {
        assertEquals("stavniko@csd.auth.gr", test.getEmail());
    }

    @Test
    public void setEmail() {
        assertEquals("stavniko@csd.auth.gr", test.getEmail());
        test.setEmail("ioanpism@csd.auth.gr");
        assertEquals("ioanpism@csd.auth.gr", test.getEmail());
    }

    @Test
    public void getAddress() {
        assertEquals("Giannarou 6", test.getAddress());
    }

    public @Test
    void setAddress() {
        assertEquals("Giannarou 6", test.getAddress());
        test.setAddress("Irodotou 1");
        assertEquals("Irodotou 1", test.getAddress());
    }
}
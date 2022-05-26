import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class MessageTest {

    Message message;
    Provider recipient;
    Date date;

    @Before
    public void setUp(){
        date = Calendar.getInstance().getTime();
        message = new Message("sender", "recipient", "hello there!", date);
    }

    @Test
    public void gettersTest(){
        assertEquals("sender", message.getSender());
        assertEquals("recipient", message.getRecipient());
        assertEquals("hello there!", message.getContent());
        assertEquals(date, message.getTime());
    }

    @Test
    public  void toStringTest(){
        assertEquals("From: sender\n \nhello there!\n \nSent at: " + date, message.toString());
    }

}
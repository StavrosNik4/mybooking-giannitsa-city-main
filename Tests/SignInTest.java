import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SignInTest {

    Platform giannitsa;

    @Before
    public void setUp(){
        giannitsa = new Platform();
    }

    @Test
    public void signIn() {
        assertTrue(SignIn.signIn(giannitsa, "babis", "2310"));
        assertFalse(SignIn.signIn(giannitsa, "babis", "123"));
    }
}
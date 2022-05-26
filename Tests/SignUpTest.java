import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SignUpTest {

    Platform giannitsa;
    String nickname;
    String password;
    String role;
    
    @Before
    public void setUp(){
        giannitsa = new Platform();
        nickname = "test";
        password = "testy";
        role = "Customer";
    }
    
    @Test
    public void signUp() {
        assertTrue(SignUp.signUp(giannitsa, nickname, password, role));
        assertFalse(SignUp.signUp(giannitsa, nickname, password, role)); //can't sign up the same nickname
    }
}
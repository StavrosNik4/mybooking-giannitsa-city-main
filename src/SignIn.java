/**
 * class for the sign in method
 */

public class SignIn {

    /**
     *  Method for the sign in process. It checks if the username exist in the platform and if the username's password is equal with the given password.
     * @return true if the sign in process complete successfully, false if the sign in process fails
     */

    public static boolean signIn(Platform giannitsa, String nickname, String password)
    {
        return giannitsa.getUsers().containsKey(nickname) && giannitsa.getUsers().get(nickname).getPassword().equals(password);
    }

}

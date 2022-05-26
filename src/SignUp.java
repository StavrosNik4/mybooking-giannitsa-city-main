/**
 * Class for the sign-up method
 */

public class SignUp {

    /**
     *  Firstly, checks if there is the nickname of the new user already in the platform.
     *  If the nickname is already in the platform returns false and stops the sign-up method.
     *  Else, it creates a new user and adds it to the platform after asking the rank {@code Provider or Customer}.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param nickname must be a String for nickname of the new user.
     * @param password must be a String for the password of the new user.
     * @param role must be a string for the role of the user.
     * @return true if sign-up is completed and false if not completed.
     */
    public static boolean signUp(Platform giannitsa, String nickname, String password, String role)
    {
        if(giannitsa.getUsers().containsKey(nickname))
            return false;

        User tmp;

        if(role.equalsIgnoreCase("Provider"))
            tmp = new Provider(nickname,password);
        else
            tmp = new Customer(nickname, password);

        giannitsa.addUser(tmp);

        return true;
    }
}
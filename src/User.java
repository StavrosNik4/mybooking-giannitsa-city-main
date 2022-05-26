import java.io.Serializable;
import java.util.ArrayList;

/**
 * This abstract class represents the general user of the platform.
 * It saves the user information in 5 Strings and one ArrayList for the Messages.
 */

public abstract class User implements Serializable {
    private final String nickname;
    private String password;
    protected String rank;
    private final ArrayList<Message> messages;
    private String email;
    private String address;

    /**
     * User constructor.
     * It initializes the user's nickname, password, rank, the messages array list, email and address.
     * @param nickname must be a string for the user's nickname.
     * @param password must be a string for the user's password.
     */

    User(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
        this.rank = "none";
        this.messages = new ArrayList<>();
        this.email = "";
        this.address = "";
    }

    //getters

    /**
     * Nickname getter.
     * @return user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Password getter.
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Rank getter.
     * @return user's rank
     */
    public String getRank(){
        return rank;
    }

    /**
     * Getter for the address.
     * @return the user's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for the email.
     * @return the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Messages' getter.
     * @return user's messages array list.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    //setters

    /**
     * Setter for the password.
     * @param password must be String for the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for the address.
     * @param address must be a string for the user's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setter for the email.
     * @param email must be a string for the user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    //methods

    /**
     * Method that adds a new message to the user's message array list.
     * @param message must be a string to add to the list.
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }
}

import java.io.Serializable;
import java.util.Date;

/**
 * Class for the messages. A Message consists of 3 strings, one for the sender's name,
 * one for the recipient's name and one for the content of the message.
 * It also contains a Date field with the exact date and time it was sent.
 */

public class Message implements Serializable {
    private final String sender;
    private final String recipient;
    private final String content;
    private final Date time;


    /**
     * Constructor of the messages.
     * @param sender the admin that sends the message.
     * @param recipient the recipient of the message.
     * @param content the content of the message.
     * @param time when the message was sent.
     */
    Message(String sender, String recipient, String content, Date time){
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.time = time;
    }

    /**
     * to string method to return the string value of the message with a specific format.
      * @return the string value of the message
     */
    @Override
    public String toString() {
        return "From: " + sender + "\n \n" + content + "\n \nSent at: " + time;
    }

    /**
     * Getter for the recipient.
     * @return the recipient.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Getter for the sender.
     * @return the sender.
     */
    public String getSender(){
        return sender;
    }

    /**
     * Getter for the content.
     * @return the content.
     */
    public String getContent(){
        return content;
    }

    /**
     * Method to return the date.
     * @return the message exact date.
     */
    public Date getTime(){
        return time;
    }


}

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

/**
 * Class for the send an (actual) email method.
 */

class SendEmail {

    /**
     * Constructor of the method.
     * @param to must be a String for the email address we want to send an email @{to}.
     */

    public SendEmail(String to){

        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "giannitsacitybooking@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("giannitsacitybooking@gmail.com", "babis58100");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            catch (Exception ignored){
                JOptionPane.showMessageDialog(null, "Something went wrong!\n Please update your email again!", "Oops!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Set Subject: header field
            message.setSubject("Καλωσορίσατε στην πλατφόρμα Giannitsa City!");

            // Now set the actual message
            message.setText("Καλωσορίσατε στην πλατφόρμα Giannitsa City! " +
                    "Την μοναδική (και μη υπαρκτή) πλατφόρμα για εύρεση καταλύματος στα Γιαννιτσά! " +
                    "Disclaimer: Μην έρθετε αν κλείσετε από εμάς παίζει να πρέπει να κοιμηθείτε σε παγκάκι στο Φιλίππειο. " +
                    "Οι δημιουργοί της πλατφόρμας δεν αναλαμβάνουν καμία ευθύνη άμα συμβεί αυτό. " +
                    "\nΜε εκτίμηση οι διαχειριστές της πλατφόρμας (λέμε τώρα): " +
                    "\nΙωάννης Πισμισάκης\n" +
                    "Σταύρος Νικολαΐδης");


            System.out.println("sending...");
            // Send message
            try {
                Transport.send(message);
                System.out.println("Sent message successfully....");
                JOptionPane.showMessageDialog(null, "Email updated successfully!", "Successfully!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ignored){
                JOptionPane.showMessageDialog(null, "Something went wrong!\n Please update your email again!", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
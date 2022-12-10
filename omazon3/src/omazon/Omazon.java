package omazon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import omazon.interfaces.FrameTemplate;

/**
 * A class that starts the Java Swing GUI program.
 * - Functionas as landing page.
 * - Handles user registration and login.
 * - Instantiates a seller and a customer.
 * - Registration initializes a user instance.
 * - Login initiates a search for the customer and opens the store. 
 */
public class Omazon extends JFrame implements FrameTemplate, ActionListener {

    // FIELDS
    CardLayout cardLayout;

    public Omazon() {
        super();

        setVisible(true);
    }
    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Omazon start = new Omazon();

            }
        });
    }

    // FUNCTIONS

    /**
     * Login button gets the store or inventory depending on the user accountt type
     */
    public void login() {
        //TODO: login portion of menu form
        // 1. enter email address
        // 2. enter password
    }

    /**
     * Registration button gets registration form
     */
    public void register() {
        //TODO: registration portion of menu form
        // 1. radio button: seller or customer
        // 2. register according
        // ...define the rest later
    }

    // EVENT LISTENERS
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}

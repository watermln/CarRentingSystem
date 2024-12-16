/*
package test.client;

import client.gui.GUI;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    @Test
    void testCreateAndShowGUI() {
        GUI gui = new GUI();
        try {
            gui.createAndShowGUI();
            System.out.println("GUI initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("GUI initialization threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testMainFrameNotNull() {
        GUI gui = new GUI();
        JFrame mainFrame = null;

        try {
            gui.createAndShowGUI();
            mainFrame = gui.getMainFrame();
            assertNotNull(mainFrame, "Main JFrame should not be null.");
            System.out.println("Main JFrame is not null.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to retrieve the main JFrame: " + e.getMessage());
        }
    }
}
*/

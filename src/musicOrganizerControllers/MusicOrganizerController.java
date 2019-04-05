package musicOrganizerControllers;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import musicOrganizerView.MusicOrganizerFrame;

/**
 * 
 * @author Mohpreet
 *
 * Entry point for the program
 */
public class MusicOrganizerController {
	
	public static void main(final String[] args) {
		// Change the look-and-feel of the program
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MusicOrganizerController().createAndShowGUI();
            }
        });
    }
	
	public void createAndShowGUI() {
		MusicOrganizerFrame mainWindow = new MusicOrganizerFrame("Music Organizer 1.0", 600, 500);
		mainWindow.setVisible(true);
	}
}

package musicOrganizerControllers;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import musicOrganizer.MusicOrganizer;
import musicOrganizerView.MusicOrganizerFrame;

public class OrganizeFolderActionListener implements ActionListener {

	private MusicOrganizerFrame musicOrganizerFrame;
	private MusicOrganizer organizer;
	private TextArea displayArea;
	
	public OrganizeFolderActionListener(MusicOrganizerFrame musicOrganizerFrame) {
		this.musicOrganizerFrame = musicOrganizerFrame;
		this.organizer = musicOrganizerFrame.getOrganizer();
		this.displayArea = musicOrganizerFrame.getFolderContentsTField();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.displayArea.setText("Starting organization...");
		// organizer.organize() returns false upon failing to organize all files
		if (!this.organizer.organize())
			this.showFailure();
		else
			this.displayArea.append("\n" + "Organization complete!");
	}
	
	/**
	 * this.showFailure() outputs a failure state window to alert
	 * the user of a failed organization attempt
	 */
	private void showFailure() {
		JOptionPane.showMessageDialog(this.musicOrganizerFrame,
				"Operation Failed." + "\n" + "Check write permissions.",
				"Error", JOptionPane.ERROR_MESSAGE);
	}

}

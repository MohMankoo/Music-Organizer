package musicOrganizerControllers;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import musicOrganizer.MusicOrganizer;
import musicOrganizerView.MusicOrganizerFrame;

/**
 * 
 * @author Mohpreet
 *
 * Searches the current folder selected in the directory of the program
 */
public class SearchFolderActionListener implements ActionListener {

	private MusicOrganizerFrame musicOrganizerFrame;
	private MusicOrganizer organizer;
	
	private JTextField musicDirTField;
	private TextArea searchResults;
	
	public SearchFolderActionListener(MusicOrganizerFrame musicOrganizerFrame) {
		this.musicOrganizerFrame = musicOrganizerFrame;
		this.organizer = musicOrganizerFrame.getOrganizer();
		
		this.musicDirTField = musicOrganizerFrame.getMusicDirTField();
		this.searchResults = musicOrganizerFrame.getFolderContentsTField();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File[] folderContentFiles = this.organizer.getMusicFolders();
		String folderContents = "";
		
		for(File musicFolder : folderContentFiles) {
			folderContents = folderContents.concat(musicFolder.getName() + "\n");
		}
		this.searchResults.setText(folderContents);
	}
	
}

package musicOrganizerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import musicOrganizer.MusicOrganizer;
import musicOrganizerView.MusicOrganizerFrame;

public class MusicBrowserActionListener implements ActionListener {
	
	private MusicOrganizerFrame musicDirParentFrame;
	private MusicOrganizer organizer;
	
	private JFileChooser musicDirFileChooser;
	private JTextField musicDirTField;
	
	public MusicBrowserActionListener(MusicOrganizerFrame musicOrganizerFrame) {
		this.musicDirParentFrame = musicOrganizerFrame;
		this.organizer = musicOrganizerFrame.getOrganizer();
		this.musicDirTField = musicOrganizerFrame.getMusicDirTField();
		
		this.musicDirFileChooser = new JFileChooser();
		this.musicDirFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.musicDirFileChooser.setMultiSelectionEnabled(false);
		this.changeFileChooserDirectory(this.musicDirTField.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.changeFileChooserDirectory(this.musicDirTField.getText());
		
		int returnVal = this.musicDirFileChooser.showDialog(this.musicDirParentFrame, "Select Folder");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedDirectory = this.musicDirFileChooser.getSelectedFile();
			
			String chosenDir = selectedDirectory.getPath();
			this.musicDirTField.setText(chosenDir);
			this.organizer.setMusicDirectory( new File(chosenDir));
		}
	}
	
	private void changeFileChooserDirectory(String filepath) {
		File userSelectedDirectory = new File(filepath);
		
		if (userSelectedDirectory.exists()) {
			this.musicDirFileChooser.setCurrentDirectory(userSelectedDirectory);
		} else {  // else set default directory to User's music directory
			File userMusicDirectory = new File( System.getProperty("user.home") + "\\Music");
			
			this.musicDirTField.setText(userMusicDirectory.getPath());
			this.organizer.setMusicDirectory(userMusicDirectory);
			this.musicDirFileChooser.setCurrentDirectory(userMusicDirectory);
		}
	}

}

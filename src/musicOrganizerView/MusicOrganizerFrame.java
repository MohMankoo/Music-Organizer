package musicOrganizerView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import musicOrganizer.MusicOrganizer;
import musicOrganizerControllers.MusicBrowserActionListener;
import musicOrganizerControllers.OrganizeFolderActionListener;
import musicOrganizerControllers.SearchFolderActionListener;

@SuppressWarnings("serial")
public class MusicOrganizerFrame extends JFrame {
	
	private MusicOrganizer organizer;
	private JTextField musicDirTField;
	// TextArea includes auto-scrolling, JTextArea does not
	private TextArea folderContentsTField;
	
	public MusicOrganizerFrame(String title, int width, int height) {
		super(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout( new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		// Build backend components
		String musicDirPath = System.getProperty("user.home") + "\\Music";
		organizer = new MusicOrganizer(new File(musicDirPath));
		
		// Build top-half comprising of the file selection system
		JPanel topHalf = new JPanel();
		topHalf.setLayout( new BoxLayout(topHalf, BoxLayout.PAGE_AXIS));
		topHalf.setPreferredSize( new Dimension(this.getSize().width, 150));
		topHalf.setBorder(BorderFactory.createEmptyBorder(15, 2, 0, 2));
		topHalf.setBackground( new Color(225, 225, 225));
		
		JLabel appTitle = new JLabel("MUSIC ORGANIZER");
		appTitle.setFont( new Font("Consolas", Font.BOLD, 42));
		appTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel selectFolderLabel = new JLabel("Select folder:");
		selectFolderLabel.setFont( new Font("Consolas", Font.PLAIN, 16));
		selectFolderLabel.setMaximumSize( new Dimension(150, 20));
		selectFolderLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel directoryRow = new JPanel();
		directoryRow.setLayout( new BoxLayout(directoryRow, BoxLayout.LINE_AXIS));
		directoryRow.setBackground(topHalf.getBackground());
		directoryRow.setAlignmentX(CENTER_ALIGNMENT);
		
		musicDirTField = new JTextField(musicDirPath);
		musicDirTField.setFont( new Font("Consolas", Font.PLAIN, 12));
		musicDirTField.setPreferredSize( new Dimension(this.getSize().width - 140, 20));
		musicDirTField.setMaximumSize( new Dimension(this.getSize().width - 140, 20));
		directoryRow.add(musicDirTField);
		
		JButton directoryBrowserButton = new JButton("Browse");
		directoryBrowserButton.setFont( new Font("Consolas", Font.PLAIN, 14));
		directoryBrowserButton.addActionListener( new MusicBrowserActionListener(this));
		directoryRow.add(Box.createHorizontalStrut(5));
		directoryRow.add(directoryBrowserButton);
		
		topHalf.add(appTitle);
		topHalf.add(Box.createVerticalStrut(15));
		topHalf.add(selectFolderLabel);
		topHalf.add(directoryRow);
		topHalf.add(Box.createVerticalGlue());
		
		// Build bottom-half comprising of components organizing the user's music folder
		JPanel bottomHalf = new JPanel( new FlowLayout());
		int bottomHalfHeight = this.getSize().height - topHalf.getSize().height;
		bottomHalf.setPreferredSize( new Dimension(this.getSize().width, bottomHalfHeight));
		bottomHalf.setBorder(BorderFactory.createEmptyBorder(20, 5, 15, 5));
		
		folderContentsTField = new TextArea("Nothing to show");
		folderContentsTField.setFont( new Font("Consolas", Font.PLAIN, 12));
		folderContentsTField.setEditable(false);
		int folderContentsTFieldWidth = (this.getSize().width / 2 ) + 50;
		folderContentsTField.setPreferredSize( new Dimension(folderContentsTFieldWidth, 250));
		folderContentsTField.setMaximumSize( new Dimension(folderContentsTFieldWidth, 250));
		
		Box transferButtonOptions = Box.createVerticalBox();
		
		JButton searchFolderButton = new JButton ("Search folder");
		searchFolderButton.addActionListener( new SearchFolderActionListener(this));
		searchFolderButton.setAlignmentX(CENTER_ALIGNMENT);
		transferButtonOptions.add(searchFolderButton);
		
		JButton organizeFolderButton = new JButton("  Oragnize!  ");
		organizeFolderButton.addActionListener( new OrganizeFolderActionListener(this));
		organizeFolderButton.setAlignmentX(CENTER_ALIGNMENT);
		transferButtonOptions.add(Box.createVerticalStrut(15));
		transferButtonOptions.add(organizeFolderButton);
		
		bottomHalf.add(folderContentsTField);
		bottomHalf.add(Box.createHorizontalStrut(15));
		bottomHalf.add(transferButtonOptions);
		
		this.add(topHalf);
		this.add(bottomHalf);
	}
	
	/**
	 * @return the organizer
	 */
	public MusicOrganizer getOrganizer() {
		return organizer;
	}
	
	/**
	 * @return the musicDirTField
	 */
	public JTextField getMusicDirTField() {
		return musicDirTField;
	}
	
	/**
	 * @return the folderContentsTField
	 */
	public TextArea getFolderContentsTField() {
		return folderContentsTField;
	}
}

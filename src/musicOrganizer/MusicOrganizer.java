package musicOrganizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Mohpreet
 *
 * Main application interface for the program
 */
public class MusicOrganizer {
	
	private File musicDirectory;
	private File[] musicFolders;
	
	private Pattern pIncorrectFolder = Pattern.compile("^(.*)\\s*-\\s*.*$");
	
	public MusicOrganizer() {
		this(null);
	}
	public MusicOrganizer(File musicDirectory) {
		this.musicDirectory = musicDirectory;
		
		this.musicFolders = null;
		if (this.musicDirectory != null) {
			if (this.musicDirectory.exists() && this.musicDirectory.isDirectory())
				this.musicFolders = musicDirectory.listFiles();
		}
	}
	
	/**
	 * Organize this.musicDirectory
	 * @return true if the move/organization is successful for all files
	 */
	public boolean organize() {
		HashMap<File, File> artistsToAlbums = this.artistsToAlbums();
		
		for (File artistFolder : artistsToAlbums.keySet()) {
			// negative value indicates artistFolder does not already exist
			if (Arrays.binarySearch(this.musicFolders, artistFolder) < 0)
				artistFolder.mkdir();  // Make artist folder if it doesn not exist
			
			File albumFolder = artistsToAlbums.get(artistFolder);  // The value matching the key
			// The File to move the album to
			File albumDest = new File(artistFolder.getAbsolutePath() + "/" + albumFolder.getName());
			//Files.move(albumFolder.toPath(), albumDest.toPath(), StandardCopyOption.ATOMIC_MOVE);
			if (! this.moveFile(albumFolder, albumDest))
				return false;
		}
		
		// Update music Directory contents, then exit
		this.musicFolders = musicDirectory.listFiles();
		return true;
	}
	
	public HashMap<File, File> artistsToAlbums() {
		// Format: <"artist": "artist - album">
		HashMap<File, File> artistsToAlbums = new HashMap<File, File>();
		Matcher incorrectFolderMatcher;
		for (File albumFolder : this.musicFolders) {
			incorrectFolderMatcher = pIncorrectFolder.matcher(albumFolder.getName());
			
			if (incorrectFolderMatcher.matches()) {
				String artist = incorrectFolderMatcher.group(1).trim();
				File artistFolder = new File(albumFolder.getParent() + "/" + artist);
				artistsToAlbums.put(artistFolder, albumFolder);
			}
		}
		return artistsToAlbums;
	}
	
	private boolean moveFile(File sourceFile, File destFile) {
		// For non-empty folders
	    if (sourceFile.isDirectory() && sourceFile.listFiles().length != 0) {
			destFile.mkdir();  // Make album folder in artist directory
	        for (File file : sourceFile.listFiles()) {
	        	File fileDest = new File(destFile.getAbsolutePath() + "/" + file.getName());
	        	moveFile(file, fileDest); // move each file in sourceFile to new folder
	        }
	        sourceFile.delete();  // Delete original folder (which is now empty)
	     // For single files and empty directories
	    } else {
	        try {
	            Files.move(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            return true;
	        } catch (IOException e) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * @return the musicFolders
	 */
	public File[] getMusicFolders() {
		return musicFolders;
	}
	
	/**
	 * @return the musicDirectory
	 */
	public File getMusicDirectory() {
		return musicDirectory;
	}
	public void setMusicDirectory(File musicDirectory) {
		this.musicDirectory = musicDirectory;
		if (this.musicDirectory.exists() && this.musicDirectory.isDirectory())
			this.musicFolders = musicDirectory.listFiles();
	}
}

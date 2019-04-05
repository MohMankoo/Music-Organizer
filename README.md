# Music-Organizer

This program's aim to clean up a user's music directory, particularly in the case the user owns a large collection. The folders must be in the form `album_artist - album_name` for the program to operate properly. Future updates may rework this into reading the file attributes from the files inside a folder in order to determine `album_artist` and `album_name`.

Current version comes with a GUI for an easy user experience. The algorithm that reworks the folders works recursively, and is found in `models\MusicOrganizer`

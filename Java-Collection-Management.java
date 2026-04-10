import java.util.ArrayList;

public class A {
		    
		    // Private static ArrayLists to store album titles and their corresponding songs
		    private static ArrayList<String> albumTitles = new ArrayList<>();
		    private static ArrayList<ArrayList<String>> albumSongs = new ArrayList<>();
		    
		    // Static block to initialize the albums and songs
		    static {
		        // Initializing first album "Blue Hawaii"
		        albumTitles.add("Blue Hawaii");
		        ArrayList<String> blueHawaiiSongs = new ArrayList<>();
		        blueHawaiiSongs.add("Almost Always True");
		        blueHawaiiSongs.add("Can't Help Falling in Love");
		        albumSongs.add(blueHawaiiSongs);
		        
		        // Initializing second album "G.I. Blues"
		        albumTitles.add("G.I. Blues");
		        ArrayList<String> giBluesSongs = new ArrayList<>();
		        giBluesSongs.add("Tonight is So Right for Love");
		        giBluesSongs.add("Wooden Heart");
		        albumSongs.add(giBluesSongs);
		    }
		    
		    // Method to add a new album if it doesn't exist
		    public static void addAlbum(String albumName) {
		        for (int i = 0; i < albumTitles.size(); i++) {
		            if (albumTitles.get(i).equalsIgnoreCase(albumName)) {
		                System.out.println("Album '" + albumName + "' already exists.");
		                return;  // Album already exists, so return
		            }
		        }
		        // Album doesn't exist, add it
		        albumTitles.add(albumName);
		        albumSongs.add(new ArrayList<String>());
		        System.out.println("Album '" + albumName + "' added.");
		    }
		    
		    // Method to add a song to an album
		    public static void addSongToAlbum(String albumName, String songTitle) {
		        int albumIndex = -1;
		        
		        // Find the album index
		        for (int i = 0; i < albumTitles.size(); i++) {
		            if (albumTitles.get(i).equalsIgnoreCase(albumName)) {
		                albumIndex = i;
		                break;
		            }
		        }
		        
		        // If album doesn't exist, create it
		        if (albumIndex == -1) {
		            albumTitles.add(albumName);
		            albumSongs.add(new ArrayList<String>());
		            albumIndex = albumTitles.size() - 1;
		            System.out.println("Album '" + albumName + "' added.");
		        }

		        // Check for duplicate song
		        ArrayList<String> songList = albumSongs.get(albumIndex);
		        for (String song : songList) {
		            if (song.equalsIgnoreCase(songTitle)) {
		                System.out.println("Song '" + songTitle + "' already exists in album '" + albumName + "'.");
		                return;  // Song already exists, return
		            }
		        }
		        
		        // Song doesn't exist, add it
		        songList.add(songTitle);
		        System.out.println("Song '" + songTitle + "' added to the album '" + albumName + "'.");
		    }
		    
		    // Method to display all albums and their songs
		    public static void displayAllAlbums() {
		        System.out.println("Elvis Presley Discography:");
		        for (int i = 0; i < albumTitles.size(); i++) {
		            System.out.println("Album: " + albumTitles.get(i));
		            ArrayList<String> songList = albumSongs.get(i);
		            if (songList.isEmpty()) {
		                System.out.println(" (No songs)");
		            } else {
		                for (String song : songList) {
		                    System.out.println(" - " + song);
		                }
		            }
		        }
		    }

		    // Main method to demonstrate the functionality
		    public static void main(String[] args) {
		        // Try to add the "Blue Hawaii" album again
		        addAlbum("Blue Hawaii");  // Should print "Album 'Blue Hawaii' already exists."
		        
		        // Try to add the song "Can't Help Falling in Love" to "Blue Hawaii"
		        addSongToAlbum("Blue Hawaii", "Can't Help Falling in Love");  // Should print "Song 'Can't Help Falling in Love' already exists in album 'Blue Hawaii'."
		        
		        // Add a new song "Moonlight Swim" to "Blue Hawaii"
		        addSongToAlbum("Blue Hawaii", "Moonlight Swim");  // Should print "Song 'Moonlight Swim' added to the album 'Blue Hawaii'."
		        
		        // Add a new song "Big Boots" to "G.I. Blues"
		        addSongToAlbum("G.I. Blues", "Big Boots");  // Should print "Song 'Big Boots' added to the album 'G.I. Blues'."
		        
		        // Try to add the song "Big Boots" again to "G.I. Blues"
		        addSongToAlbum("G.I. Blues", "Big Boots");  // Should print "Song 'Big Boots' already exists in album 'G.I. Blues'."
		        
		        // Display all albums and songs
		        displayAllAlbums();
		    }
		

		
		
		
		
		

	}



import java.util.ArrayList;

public class lab04q2 {

		interface AlbumSearch {
		    String findAlbumOfSong(String songTitle);
		    String albumWithMostSongs();
		}

		public class ElvisDatabase implements AlbumSearch {

		    private static ArrayList<String> albumTitles = new ArrayList<>();
		    private static ArrayList<ArrayList<String>> albumSongs = new ArrayList<>();

		    static {
		        albumTitles.add("Blue Hawaii");
		        
		        ArrayList<String> blueHawaiiSarkilari = new ArrayList<>();
		        
		        blueHawaiiSarkilari.add("Almost Always True");
		        blueHawaiiSarkilari.add("Can't Help Falling in Love");
		        
		        albumSongs.add(blueHawaiiSarkilari);
		        
		        albumTitles.add("G.I. Blues");
		        
		        ArrayList<String> giBluesSarkilari = new ArrayList<>();
		        
		        giBluesSarkilari.add("Tonight is So Right for Love");
		        giBluesSarkilari.add("Wooden Heart");
		        
		        albumSongs.add(giBluesSarkilari);
		    }

	
		    public static void addAlbum(String albumName) {
		    	
		        for (int i = 0; i < albumTitles.size(); i++) {
		        	
		            if (albumTitles.get(i).equalsIgnoreCase(albumName)) {
		            	
		                System.out.println("Album '" + albumName + "' already exists.");
		               
		                return;
		            }
		        }
		        
		        albumTitles.add(albumName);
		        albumSongs.add(new ArrayList<String>());
		        
		        System.out.println("Album '" + albumName + "' added.");
		    }

		
		    public static void addSongToAlbum(String albumName, String songTitle) {
		        int albumIndex = -1;
		        for (int i = 0; i < albumTitles.size(); i++) {
		            if (albumTitles.get(i).equalsIgnoreCase(albumName)) {
		                albumIndex = i;
		                break;
		            }
		        }

		        if (albumIndex == -1) {
		            albumTitles.add(albumName);
		            albumSongs.add(new ArrayList<String>());
		            albumIndex = albumTitles.size() - 1;
		            System.out.println("Album '" + albumName + "' added.");
		        }

		        ArrayList<String> songList = albumSongs.get(albumIndex);
		        for (String song : songList) {
		            if (song.equalsIgnoreCase(songTitle)) {
		                System.out.println("Song '" + songTitle + "' already exists in album '" + albumName + "'.");
		                return;
		            }
		        }
		        songList.add(songTitle);
		        System.out.println("Song '" + songTitle + "' added to the album '" + albumName + "'.");
		    }

		    // Display all albums and songs
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

		    // Find album that contains the given song
		    @Override
		    public String findAlbumOfSong(String songTitle) {
		        for (int i = 0; i < albumTitles.size(); i++) {
		            for (String song : albumSongs.get(i)) {
		                if (song.equalsIgnoreCase(songTitle)) {
		                    return albumTitles.get(i);
		                }
		            }
		        }
		        return "Song not found.";
		    }

		    // Find the album with the most songs
		    @Override
		    public String albumWithMostSongs() {
		        if (albumTitles.isEmpty()) {
		            return "No albums available.";
		        }

		        int maxIndex = 0;
		        int maxCount = albumSongs.get(0).size();
		        for (int i = 1; i < albumTitles.size(); i++) {
		            int count = albumSongs.get(i).size();
		            if (count > maxCount) {
		                maxCount = count;
		                maxIndex = i;
		            }
		        }
		        return albumTitles.get(maxIndex);
		    }

		    // Run Q2 methods and demonstrate features
		    public static void runQ2() {
		        System.out.println("Elvis Database Analysis:");

		        // Add new album and song
		        addAlbum("Elvis is Back!");
		        addSongToAlbum("Elvis is Back!", "Fever");

		        // Try adding the album and song again to show duplicate checks
		        addAlbum("Elvis is Back!");
		        addSongToAlbum("Elvis is Back!", "Fever");

		        // Display updated discography
		        System.out.println("\nUpdated Elvis Presley Discography:");
		        displayAllAlbums();

		        // Search for a song in the database
		        System.out.println("\nQ2 Search Results:");
		        System.out.println("Searching album for song: \"Blue Suede Shoes\"");
		        String result = new lab04q1().findAlbumOfSong("Blue Suede Shoes");
		        System.out.println("Result: " + result);

		        // Search for a song that exists
		        System.out.println("Searching album for song: \"Fever\"");
		        result = new lab04q1().findAlbumOfSong("Fever");
		        System.out.println("Result: " + result);

		        // Find the album with the most songs
		        System.out.println("\nAlbum with the most songs: " + new lab04q1().albumWithMostSongs());
		    }

		    // Main method to demonstrate all features
		    public static void main(String[] args) {
		        // Q1 demonstration
		        addAlbum("Blue Hawaii");
		        addSongToAlbum("Blue Hawaii", "Almost Always True");
		        addSongToAlbum("Blue Hawaii", "Can't Help Falling in Love");
		        addSongToAlbum("Blue Hawaii", "Moonlight Swim");
		        
		        addAlbum("G.I. Blues");
		        addSongToAlbum("G.I. Blues", "Tonight is So Right for Love");
		        addSongToAlbum("G.I. Blues", "Big Boots");
		        addSongToAlbum("G.I. Blues", "Wooden Heart");

		        addAlbum("Blue Hawaii");  // Should print "Album 'Blue Hawaii' already exists."
		        addSongToAlbum("Blue Hawaii", "Can't Help Falling in Love");  // Should print "Song 'Can't Help Falling in Love' already exists."

		        displayAllAlbums();

		        // Run Q2 features
		        runQ2();
		    }
		}
}



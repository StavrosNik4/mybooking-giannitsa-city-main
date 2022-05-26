import java.util.ArrayList;

/**
 * A class to hold details of audio files. This version can play the files.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer {

    private final ArrayList<String> files;
    private final MusicPlayer player;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer() {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }

    /**
     * Add a file to the collection.
     *
     * @param filename The file to be added.
     */
    public void addFile(String filename) {
        files.add(filename);
    }

    /**
     * List a file from the collection.
     *
     * @param index The index of the file to be listed.
     */
    public void listFile(int index) {
        if (index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Start playing a file in the collection. Use stopPlaying() to stop it
     * playing.
     *
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index) {
        String filename = files.get(index);
        player.startPlaying(filename);
    }

    /**
     * Stop the player.
     */
    public void stopPlaying() {
        player.stop();
    }
}

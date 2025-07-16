// State interface
interface PlayerState {

    void pressPlay(MediaPlayer player);
}

// Concrete State: Playing
class PlayingState implements PlayerState {

    public void pressPlay(MediaPlayer player) {
        System.out.println("Pausing the music.");
        player.setState(new PausedState());
    }
}

// Concrete State: Paused
class PausedState implements PlayerState {

    public void pressPlay(MediaPlayer player) {
        System.out.println("Resuming the music.");
        player.setState(new PlayingState());
    }
}

// Context
class MediaPlayer {

    private PlayerState state;

    public MediaPlayer() {
        state = new PausedState();  // initial state
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void pressPlay() {
        state.pressPlay(this);
    }
}

// Client
public class Main {

    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();
        player.pressPlay(); // → Resuming the music.
        player.pressPlay(); // → Pausing the music.
        player.pressPlay(); // → Resuming the music.
    }
}

package bg.tu_varna.sit;

public class AlreadyTaken extends RuntimeException {
    public AlreadyTaken(String message) {
        super(message);
    }
}

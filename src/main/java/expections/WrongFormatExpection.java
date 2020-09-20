package expections;

public class WrongFormatExpection extends Exception {
    private int guiltyBit;

    public WrongFormatExpection(String message) {
        super(message);
        this.guiltyBit = -1;
    }

    public WrongFormatExpection(String message, int guiltyBit) {
        super(message);
        this.guiltyBit = guiltyBit;
    }

    public int getGuiltyBit(){
        return this.guiltyBit;
    }
}

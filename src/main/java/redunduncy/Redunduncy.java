package redunduncy;

import expections.WrongFormatExpection;

public interface Redunduncy {
    String introduceRedunduncy(String bytes) throws WrongFormatExpection;
    String getValue(String bytes) throws WrongFormatExpection;
}

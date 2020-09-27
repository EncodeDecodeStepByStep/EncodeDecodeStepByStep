import org.junit.Test;
import redunduncy.CRC;

public class CRCTest {

    CRC crc = new CRC();

    @Test
    public void realizarTestCRC() {
        crc.calculateCRC8("01000010", "01000011");
    }
}

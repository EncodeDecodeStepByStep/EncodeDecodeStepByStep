package utils;

public class BitWriter {

    private byte nthBit = 0;
    private int index = 0;
    private byte[] data;

    public BitWriter( int nBits ) {
        this.data = new byte[(int)Math.ceil(nBits / 8.0)];
    }

    public void writeBit(boolean bit) {
        if( nthBit >= 8) {
            nthBit = 0;

            index++;
            if( index >= data.length) {
                throw new IndexOutOfBoundsException();
            }
        }
        byte b = data[index];

        int mask = (1 << (7 - nthBit));

        if( bit ) {
            b = (byte)(b | mask);
        }
        data[index] = b;
        nthBit++;
    }

    public byte[] toArray() {
        byte[] ret = new byte[data.length];
        System.arraycopy(data, 0, ret, 0, data.length);
        return ret;
    }

    public static byte[] convert( String bits ) {
        BitWriter bw = new BitWriter(6);
        String strbits = "";
        for( int i = 0; i < strbits.length(); i++) {
            bw.writeBit( strbits.charAt(i) == '1');
        }

        return bw.toArray();
    }

}
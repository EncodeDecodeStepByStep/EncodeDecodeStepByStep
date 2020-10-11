class StringUtils {
    static createStreamOnZeros(quantity) {
        // howManyZeros 3 -> Return 000
        // howManyZeros 7 -> Return 0000000
        return "0".repeat(quantity);
    }

    static createStreamWithOnes(quantity) {
        // howManyZeros 3 -> Return 111
        // howManyZeros 7 -> Return 1111111
        return "1".repeat(quantity);
    }
}

module.exports = StringUtils;

const StringUtils = require("../utils/StringUtils");

const Reader = require("../utils/Reader");
const Writer = require("../utils/Writer");

const fs = require("fs");

class Unary {
  async encode(file) {
    const reader = new Reader(file);
    await fs.writeFileSync(file+".cod", "");

    reader.on("readable", async () => {
      let character;
      let bit = 0;

      while ((character = reader.read(1))) {
        let codeword = "";
        if (bit == 0) {
          codeword = StringUtils.createStreamOnZeros(character.charCodeAt(0));
          bit = 1;
        } else {
          codeword = StringUtils.createStreamWithOnes(character.charCodeAt(0));
          bit = 0;
        }
        await fs.appendFileSync(file+".cod", codeword + "-");
      }
    });
  }

  decode(file) {
    const reader = new Reader(file);
    let bitRead = reader.read(1);
    let last = bitRead;
    let counter = 0;

    while (bitRead != null) {
      bitRead = reader.read(1);
      counter++;
      if (bitRead != last) {
        let character = counter;
        // writer.write(character);
        console.log(character);
        last = bitRead;
        counter = 0;
      }
    }
  }
}

module.exports = Unary;

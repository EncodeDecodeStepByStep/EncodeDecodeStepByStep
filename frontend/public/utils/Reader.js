const fs = require("fs");

class Reader{
    constructor(file){
        return fs.createReadStream(file, {
            encoding: "utf8",
            fd: null,
          });
    }
}

module.exports = Reader;
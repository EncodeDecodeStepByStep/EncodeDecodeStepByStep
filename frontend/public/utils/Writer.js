
class Writer {

  write(filename, content) {
    fs.writeFileSync(filename, content);
  }
}

module.exports = Writer;

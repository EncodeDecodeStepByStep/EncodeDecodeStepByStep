const { app, BrowserWindow } = require('electron')
const isDev = require('electron-is-dev');

const childProcess = require('child_process')
const path = require('path');

function createWindow () {

  // const jarPath = path.resolve(__dirname,'..', 'backend_jar', 'encodedecodestepbystep-0.0.1-SNAPSHOT.jar') TODO descomentar para build final
  // console.log(jarPath)
  // const child = childProcess.exec( 'java -jar '+jarPath);

  const win = new BrowserWindow({
    width: 800,
    height: 800,
    webPreferences: {
      nodeIntegration: true
    },
    icon: __dirname + '/icon.jpg'
  })

  win.on('close', (e) => {
    // const kill = require('tree-kill'); //TODO descomentar e achar uma solução que funcione
    // kill(child.pid);
    // console.log("CHEGOU AQUI?????????????????")
    // child.kill()
  })

  win.loadURL(
    isDev? 'http://localhost:3000': `file://${path.join(__dirname, "../build/index.html")}`
  )
}

app.whenReady().then(createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    createWindow()
  }
})

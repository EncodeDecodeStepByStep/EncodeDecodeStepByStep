const {app, BrowserWindow} = require('electron')
const isDev = require('electron-is-dev');

const path = require("path");

function createWindow() {

    const win = new BrowserWindow({
        width: 800,
        height: 800,
        webPreferences: {
            nodeIntegration: true
        },
        icon: __dirname + '/favicon.png'
    })

    win.loadURL(
        isDev ? 'http://localhost:3000' : `file://${path.join(__dirname, "../build/index.html")}`
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

const { app, BrowserWindow } = require('electron')

let isDev = false
let mainWindow

const createWindow = () => {
    isDev = process.argv.findIndex((a) => a === '--development-mode') !== -1;
    const runningMode = isDev ? 'development' : 'production';

    console.log(`Application is running in ${runningMode} mode.`);

    mainWindow = new BrowserWindow({
        width: 1200,
        height: 800,
        autoHideMenuBar: true,
        webPreferences: {
            nodeIntegration: true,
            enableRemoteModule: true,
            webSecurity: false,
            contextIsolation: false,
        },
    });

    mainWindow.loadURL('http://127.0.0.1:3000');
    // if (isDev) {
    //     mainWindow.loadURL('http://127.0.0.1:8000');
    // } else {
    //     mainWindow.loadFile('./build/app/index.html');
    // }
};

app.on('ready', createWindow);
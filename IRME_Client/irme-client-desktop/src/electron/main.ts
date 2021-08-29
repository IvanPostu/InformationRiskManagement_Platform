import { app, BrowserWindow } from 'electron';

let isDev = false;
process.env['ELECTRON_DISABLE_SECURITY_WARNINGS'] = isDev ? 'true' : 'false';

let mainWindow: BrowserWindow;

const createWindow = () => {
    isDev = process.argv.findIndex((a) => a === '--development-mode') !== -1;
    const runningMode = isDev ? 'development' : 'production';

    console.log(`Application is running in ${runningMode} mode.`);

    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true,
            enableRemoteModule: true,
            webSecurity: false,
            contextIsolation: false,
        },
        frame: false,
    });

    if (isDev) {
        mainWindow.loadURL('http://127.0.0.1:8000');
    } else {
        mainWindow.loadFile('./build/app/index.html');
    }
};

app.on('ready', createWindow);

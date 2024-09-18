package core;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class DesktopLauncher {
	

	
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Game_Tiled(), getDefaultConfiguration());
    }

    /**
     * @return
     */
    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("TESTALO CAPO PT.2!");
        
        configuration.useVsync(true);
        
        Monitor primary = Lwjgl3ApplicationConfiguration.getPrimaryMonitor();
        DisplayMode desktopMode = Lwjgl3ApplicationConfiguration.getDisplayMode(primary);
        configuration.setFullscreenMode(desktopMode);
        
        //// Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(desktopMode.refreshRate);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        /*configuration.setWindowedMode(960, 640);*/
        return configuration;
    }
    
    

}

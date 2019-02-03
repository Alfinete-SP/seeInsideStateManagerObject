package mygame;

import com.jme3.app.ChaseCameraAppState;
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.ResetStatsState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.AppState;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.app.state.VideoRecorderAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.debug.BulletDebugAppState;
import com.jme3.cinematic.Cinematic;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Class to access AppStateManager and see the app states that are connected therein.
 */
public class Main extends SimpleApplication {
    
    AppState[] stateArray;
    Method getStatesMethod;
    private int counter = 0;

    public static void main(String[] args) 
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
        {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        
        TestAppState testState = new TestAppState();
        stateManager.attach(testState);

        listStateManagerMethods();
        }

            
    @Override
    public void simpleUpdate(float tpf) {
        counter++;
        if (counter == 10) {
            outputAppStateData();
        }
        if (counter == 200) {
            removeAppStates();
            outputAppStateData();
            System.out.println("");
            System.out.println("Calling stop() ...");
            stop();
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void outputAppStateData() {
        System.out.println("");
        System.out.println("OUTPUTTING APP STATE DATA IN STATEMANAGER ...");
        try {
            Class stateManagerClass = stateManager.getClass();

            getStatesMethod = stateManagerClass.getDeclaredMethod("getStates");
            getStatesMethod.setAccessible(true);
            System.out.println("is accessable: " + getStatesMethod.isAccessible());
            
            Object stateArrayObject = getStatesMethod.invoke(stateManager);    
            System.out.println("StateObjectArray: " + stateArrayObject.toString());
            
            stateArray = (AppState[])stateArrayObject;
            //System.out.println("StateArray: " + stateArray.toString());
            
            System.out.println("state array is null: " + (stateArray == null));
            
            System.out.println("stateArray length = " + stateArray.length);
            
            for (int i = 0; i < stateArray.length; i++) {
                System.out.println("State is: " + stateArray[i].toString());
            }
        } catch (Exception e) {
        }
    }

    private void listStateManagerMethods() {
        System.out.println("");
        System.out.println("LISTING APPSTATEMANAGER METHODS ... ");
        
        try {
           Class stateManagerClass = stateManager.getClass();
            
            Method[] methodArray = stateManagerClass.getDeclaredMethods();
            
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(methodArray[i].toString());
            }
            
            System.out.println(""); 
        } catch (Exception e) {
        }
    }

    private void removeAppStates() {
        System.out.println("");
        System.out.println("REMOVING APP STATES IN STATE MANAGER ...");
        System.out.println("flycam      detached: " + 
                stateManager.detach(stateManager.getState(FlyCamAppState.class)));
        
        System.out.println("debugKeys   detached: " + 
                stateManager.detach(stateManager.getState(DebugKeysAppState.class)));
        
        System.out.println("stats       detached: " + 
                stateManager.detach(stateManager.getState(StatsAppState.class)));
        
        System.out.println("reset stats detached: " + 
                stateManager.detach(stateManager.getState(ResetStatsState.class)));
        
        System.out.println("bulletdebug detached: " + 
                stateManager.detach(stateManager.getState(BulletDebugAppState.class)));
        
        System.out.println("bullet      detached: " + 
                stateManager.detach(stateManager.getState(BulletAppState.class)));
        
        System.out.println("chaseCamera detached: " + 
                stateManager.detach(stateManager.getState(ChaseCameraAppState.class)));
        
        System.out.println("cinematic   detached: " + 
                stateManager.detach(stateManager.getState(Cinematic.class)));
        
        System.out.println("EnvironCam  detached: " + 
                stateManager.detach(stateManager.getState(EnvironmentCamera.class)));
        
        System.out.println("screen shot detached: " + 
                stateManager.detach(stateManager.getState(ScreenshotAppState.class)));
        
        System.out.println("vid record  detached: " + 
                stateManager.detach(stateManager.getState(VideoRecorderAppState.class)));
    }
}

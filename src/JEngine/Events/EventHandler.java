package JEngine.Events;

import java.util.HashMap;

public class EventHandler {

    private HashMap<Integer, Boolean> keyEvents;
    private KeyCallback callbackFunction;

    public EventHandler(){
        keyEvents = new HashMap<>();
    }

    public void Poll(){
        //System.out.println(keyEvents.keySet().size());
        /*for(int i = 0; i < keyEvents.keySet().size(); i++){
            int keyCode = (Integer) keyEvents.keySet().toArray()[i];
            if(IsKeyDown(keyCode)){
                callbackFunction.process();
            }else{
                //keyEvents.remove(keyCode);
            }
        }*/
        callbackFunction.process();
    }

    public void SetKeyCallback(IKeyCallback callbackFunction){
        this.callbackFunction = (KeyCallback) callbackFunction;
    }

    public boolean IsKeyDown(int keyCode){
        if(keyEvents.containsKey(keyCode)){
            return keyEvents.get(keyCode);
        }
        return false;
    }

    public boolean IsKeyUp(int keyCode){
        return !IsKeyDown(keyCode);
    }

    public HashMap<Integer, Boolean> getKeyEvents(){
        return keyEvents;
    }

    public void SetKeyState(int keyCode, boolean state){
        keyEvents.put(keyCode, state);
    }
}

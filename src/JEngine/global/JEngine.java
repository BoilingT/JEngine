package JEngine.global;

import JEngine.Events.EventHandler;
import JEngine.Events.KeyCallback;
import JEngine.Graphics.CanvasPanel;
import JEngine.Window.WindowHandler;

import java.awt.event.*;

public abstract class JEngine {

    private CanvasPanel canvas;
    private WindowHandler window;

    public static EventHandler eventHandler;
    private static KeyListener keyListener;
    private static ComponentListener componentListener;
    private static MouseMotionListener mouseMotionListener;

    private double DeltaTime = 0;
    private double FixedDeltaTime = 1000;
    private int FPS = 0;

    private double mousex = 0;
    private double mousey = 0;

    private static boolean windowShouldClose = false;
    private boolean Running = false;


    public double GetFixedDeltaTime(){
        return FixedDeltaTime;
    }

    public void SetFixedDeltaTime(double time){
        this.FixedDeltaTime = time;
    }

    public int GetFramesPerSecond(){
        return FPS;
    }

    public CanvasPanel getCanvas(){
        return canvas;
    }

    public double getMousex() {
        return mousex;
    }

    public double getMousey() {
        return mousey;
    }

    public JEngine(){
        System.out.println("Initializing window");
        window = new WindowHandler(700, 700, "JEngine");

        eventHandler = new EventHandler();

        System.out.println("Adding listeners");
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                eventHandler.SetKeyState(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                eventHandler.SetKeyState(e.getKeyCode(), false);
            }
        };
        window.addKeyListener(keyListener);

        componentListener = new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                //canvas.setSize(window.getSize().width, window.getSize().height);
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        };
        window.addComponentListener(componentListener);

        mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousex = e.getX();
                mousey = e.getY();
            }
        };
        window.addMouseMotionListener(mouseMotionListener);

        eventHandler.SetKeyCallback(new KeyCallback(){
            @Override
            public void process(){
                ProcessInput();
            }
        });

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Stop();
            }
        });

        canvas = new CanvasPanel(window.getSize().width, window.getSize().height);

        System.out.println("Adding panels");
        window.add(canvas);
        window.Show();

        System.out.println("JEngine Initialized");

    }

    public void Run(){
        Running = true;
        Init();

        long prevTime = System.nanoTime();
        long frames = 0;
        double timePassedUpdate = 0;
        double timePassedFixedUpdate = 0;

        while(!windowShouldClose){
            long time = System.nanoTime();
            DeltaTime = (double) ((time - prevTime) / 1e6);
            //System.out.println(DeltaTime);

            ++frames;

            // FPS = F / S
            //
            Update(DeltaTime/1000);
            if((timePassedUpdate += DeltaTime) >= 1000.f/180.f){
                if(Running){
                    canvas.Draw();
                    window.repaint();
                }
                //System.out.println(frames/timePassedFixedUpdate);
                //System.out.println(1/(timePassedUpdate/1000));
                FPS = (int)(1000.f/timePassedUpdate);
                frames = 0;
                timePassedUpdate = 0;
            }

            if((timePassedFixedUpdate += DeltaTime) >= FixedDeltaTime){
                FixedUpdate((double) FixedDeltaTime);
                timePassedFixedUpdate = 0;
            }

            eventHandler.Poll();

            prevTime = time;

        }
        System.out.println("JEngine Exited");
        window.Close();
    }

    public void Pause(){
        Running = false;
    }

    public void Stop(){
        windowShouldClose = true;
    }

    public boolean isRunning(){
        return Running;
    }

    private void ProcessInput(){
        //System.out.println("Key Down");
        if(eventHandler.IsKeyDown(KeyEvent.VK_ESCAPE)) Stop();
        if(eventHandler.IsKeyDown(KeyEvent.VK_PAUSE)) if(isRunning()) Pause();
    }

    public abstract void Init();
    public abstract void Update(double DeltaTime);
    public abstract void FixedUpdate(double FixedDeltaTime);
}

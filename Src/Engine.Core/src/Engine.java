import virtualDataBus.Container;

import java.util.Timer;
import java.util.TimerTask;

public class Engine
{
    public static final int BUS_READ_PERIOD_SEC = 1;

    private final Container _container;
    private final IEngineBlock _engineBlock;
    private final Timer _messagePump;

    public Engine(Container container) {
        _container = container;
        _engineBlock = new EngineBlock(_container,_container);
        _messagePump = new Timer();
    }

    public Engine(Container container, IEngineBlock engineBlock) {
        _container = container;
        _engineBlock = engineBlock;
        _messagePump = new Timer();
    }

    public void Connect(){
        _messagePump.schedule(new TimerTask() {
            @Override
            public void run() {
                Signal();
            }
        }, BUS_READ_PERIOD_SEC *1000);
    }

    public void Disconnect(){
        _messagePump.cancel();
    }

    private void Signal(){

    }
}

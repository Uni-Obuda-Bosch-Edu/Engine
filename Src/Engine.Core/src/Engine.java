import virtualDataBus.Container;

import java.util.Timer;
import java.util.TimerTask;

public class Engine
{
    private final int _busReadPeriodMilisec;
    private final Container _container;
    private final IEngineBlock _engineBlock;
    private final Timer _messagePump;

    private boolean _isConnected;

    public Engine(Container container, IEngineBlock engineBlock, int busReadPeriodMilisec) {
        _container = container;
        _engineBlock = engineBlock;
        _messagePump = new Timer();
        _busReadPeriodMilisec = busReadPeriodMilisec;
    }

    public void Connect(){
        _messagePump.schedule(new TimerTask() {
            @Override
            public void run() {
                Signal();
            }
        }, _busReadPeriodMilisec);
        _isConnected = true;
    }

    public void Disconnect(){
        _messagePump.cancel();
        _isConnected = false;
    }

    private void Signal(){
        _engineBlock.Signal();
    }

    public boolean IsConnected() {
        return _isConnected;
    }

    public int GetBusReadPeriodMilisec() {
        return _busReadPeriodMilisec;
    }
}

import virtualDataBus.Container;

import java.util.Timer;
import java.util.TimerTask;

public class Engine
{
    private final int _busReadPeriodMs;
    private final Container _container;
    private final IEngineBlock _engineBlock;
    private final Timer _messagePump;

    private boolean _isConnected;

    public Engine(Container container, IEngineBlock engineBlock, int busReadPeriodMs) {
        _container = container;
        _engineBlock = engineBlock;
        _messagePump = new Timer();
        _busReadPeriodMs = busReadPeriodMs;
    }

    public void Connect(){
        if(_isConnected)
            return;

        _messagePump.schedule(new TimerTask() {
            @Override
            public void run() {
                Signal();
            }
        }, _busReadPeriodMs);
        _isConnected = true;
    }

    public void Disconnect(){
        _messagePump.cancel();
        _isConnected = false;
        _container.setEngineRevolution(0);
    }

    private void Signal(){
        _engineBlock.Signal();
    }

    public boolean IsConnected() {
        return _isConnected;
    }

    public int GetBusReadPeriodMs() {
        return _busReadPeriodMs;
    }
}

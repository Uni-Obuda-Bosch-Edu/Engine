import static org.junit.Assert.*;

import org.junit.Test;
import virtualDataBus.Container;

public class EngineTest {
    private final int BUS_READ_PERIOD_MS = 1000;

    Container _bus;
    Engine _engine;
    EngineSpy _engEngineSpy;

    @org.junit.Before
    public void setUp() throws Exception {
        _bus = Container.getInstance();
        _engEngineSpy = new EngineSpy();
        _engine = new Engine(_bus,_engEngineSpy,BUS_READ_PERIOD_MS);
    }

    @org.junit.After
    public void setDown() throws Exception {
        _engine.Disconnect();
    }

    @Test
    public void Engine_connected_test() throws InterruptedException {
        //Arrange
        boolean initState = _engine.IsConnected();

        //Act
        _engine.Connect();
        Thread.sleep(_engine.GetBusReadPeriodMs());

        //Assert
        assertTrue(_engine.GetBusReadPeriodMs() == BUS_READ_PERIOD_MS);
        assertFalse(initState);
        assertTrue(_engine.IsConnected());
        assertTrue(_engEngineSpy.GetIsSignaled());
    }

    @Test
    public void Engine_connect_disconnect_test() throws InterruptedException {
        //Arrange
        boolean initState = _engine.IsConnected();

        //Act
        _engine.Connect();
        _engine.Disconnect();
        Thread.sleep(_engine.GetBusReadPeriodMs());

        //Assert
        assertTrue(_engine.GetBusReadPeriodMs() == BUS_READ_PERIOD_MS);
        assertFalse(initState);
        assertFalse(_engine.IsConnected());
        assertFalse(_engEngineSpy.GetIsSignaled());
    }
}
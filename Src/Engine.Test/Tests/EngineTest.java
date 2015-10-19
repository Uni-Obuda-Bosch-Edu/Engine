import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import virtualDataBus.Container;

public class EngineTest {

    Container _bus;
    Engine _engine;

    @org.junit.Before
    public void setUp() throws Exception {
        _bus = Container.getInstance();
        _engine = new Engine(_bus,new EngineBlock(_bus,_bus),1000);
    }

    @org.junit.After
    public void setDown() throws Exception {
        _engine.Disconnect();
    }

    @Test
    public void Engine_connection_test()
    {
        //Arrange
        boolean initState = _engine.IsConnected();

        //Act
        _engine.Connect();

        //Assert
        assertFalse(initState);
        assertTrue(_engine.IsConnected());
    }
}
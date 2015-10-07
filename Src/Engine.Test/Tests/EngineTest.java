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
        _engine = new Engine(_bus,new TestEngine());
    }

    @Test
    public void Engine_connect_test()
    {
        //Arrange

        //Act
        _engine.Connect();

        //Assert

    }

    public void Engine_disconnect_test()
    {
        //Arrange

        //Act
        _engine.Disconnect();

        //Assert

    }

    public void Engine_reconnect_test()
    {
        //Arrange
        //Act
        _engine.Disconnect();
        _engine.Connect();

        //Assert

    }
}
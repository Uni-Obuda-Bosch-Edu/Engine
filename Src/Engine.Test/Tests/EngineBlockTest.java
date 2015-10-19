import static org.junit.Assert.*;

import com.sun.deploy.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;
import virtualDataBus.Container;

import java.util.ArrayList;

public class EngineBlockTest {

    private EngineBlock _engineBlock;
    private Container _bus;

    @org.junit.Before
    public void setUp() throws Exception {
        _bus = Container.getInstance();
        _engineBlock = new EngineBlock(_bus, _bus);
    }

    private ArrayList<Double> DoTest(double start, double...throttleStates){
        ArrayList<Double> result = new ArrayList<Double>();

        double nextRev = start;
        for (int i = 0; i < throttleStates.length; i++)
            result.add(_engineBlock.CalcNextRev(nextRev, throttleStates[i]));

        return result;
    }

    private ArrayList<Double> List(double...doubles){

        ArrayList<Double> result = new ArrayList<Double>();

        for (int i = 0; i < doubles.length; i++) {
            result.add(doubles[i]);
        }

        return result;
    }

    @Test
    public void From_500_to_1000()
    {
        Assert.assertEquals(List(500), DoTest(500, 1000));
    }
}
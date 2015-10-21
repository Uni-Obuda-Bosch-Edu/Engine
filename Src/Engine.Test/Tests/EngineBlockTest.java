import static org.junit.Assert.*;

import com.sun.deploy.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;
import virtualDataBus.Container;

import java.util.ArrayList;

public class EngineBlockTest {

    private EngineBlock _engineBlock;
    private Container _bus;
    private ArrayList<Double> _expectedResult;

    @org.junit.Before
    public void setUp() throws Exception {
        _bus = Container.getInstance();
        _engineBlock = new EngineBlock(_bus, _bus);

        _expectedResult = new ArrayList<Double>();
    }

    @Test
    public void Rev_range_test(){

    }

    @Test
    public void From_500_to_6025()
    {
        ArrayList<Double> result = new ArrayList<Double>();

        double nextRev = 500;
        for (int i = 0; i < EngineBlock.MIN_TO_MAX_REV_TIME_MS; i++)
            result.add(_engineBlock.CalcNextPositiveRev(nextRev, 6025));

        Assert.assertEquals(_expectedResult, result);
    }
}
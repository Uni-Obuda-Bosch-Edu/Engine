import busInterface.Engine_Out;
import busInterface.Public_In;

public class EngineBlock implements IEngineBlock{
    public final double MAX_REV_RPM = 7000;
    public final double MIN_REV_RPM = 500;
    public final double OPTIMAL_REV_STEP_RPM = 500;

    private final Public_In _inBus;
    private final Engine_Out _outBus;

    private double _currentRev;

    public EngineBlock(Public_In inBus, Engine_Out outBus) {
        this._inBus = inBus;
        this._outBus = outBus;
    }

    public double GetCurrentRev(){
        return _currentRev;
    }

    @Override
    public void Signal() {
        double targetRev = _inBus.getGasPedalPercentage();

        double sign = targetRev >= _currentRev ? 1 : -1;
        _currentRev = sign*CalcNextRev(_currentRev,targetRev);

        _outBus.setEngineRevolution(((int) _currentRev));
    }

    public double CalcNextRev(double currentRev, double targetRev ){
        double nextRev = Math.abs(targetRev - currentRev);

        nextRev = Math.max(nextRev,MIN_REV_RPM);
        nextRev = Math.min(nextRev, MAX_REV_RPM);

        return nextRev;
    }
}

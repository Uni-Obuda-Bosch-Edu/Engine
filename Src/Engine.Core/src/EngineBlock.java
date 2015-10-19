import busInterface.Engine_Out;
import busInterface.Public_In;

public class EngineBlock implements IEngineBlock{
    public static final int MIN_REV_RPM = 500;
    public static final int MAX_REV_RPM = 7000;
    public static final int ENGINE_CENTER_RPM = 3000;
    public static final int MIN_TO_MAX_REV_TIME_MS =2000;
    public static final int BUS_SAMPLING_TIME_MS = MIN_TO_MAX_REV_TIME_MS / 100;
    public static final int OPTIMAL_STEPPING = MIN_TO_MAX_REV_TIME_MS / BUS_SAMPLING_TIME_MS;

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

    public void SetCurrentRev(double rev){
        rev = Math.max(rev, MIN_REV_RPM);
        rev = Math.min(rev, MAX_REV_RPM);

        _currentRev = rev;
    }

    @Override
    public void Signal() {
        double targetRev = GetTartgetRev();

        double rev;
        if(targetRev >= _currentRev)
            rev = CalcNextPositiveRev(_currentRev, targetRev);
        else
            rev = CalcNextNegativeRev(_currentRev, targetRev);

        SetCurrentRev(rev);
        //conversation
        _outBus.setEngineRevolution(((int) GetCurrentRev()));
    }

    private double GetTartgetRev() {
        double targetPercentage = _inBus.getGasPedalPercentage();

        targetPercentage = Math.max(targetPercentage,0);
        targetPercentage = Math.min(targetPercentage, 100);

        //?
        return MAX_REV_RPM/targetPercentage;
    }

    public double CalcNextPositiveRev(double currentRev, double targetRev ){
        double nextRev = currentRev + OPTIMAL_STEPPING*(currentRev/ENGINE_CENTER_RPM);

        nextRev = Math.min(nextRev, targetRev);

        return nextRev;
    }

    public double CalcNextNegativeRev(double currentRev, double targetRev ){
        double nextRev = currentRev + OPTIMAL_STEPPING*(currentRev/ENGINE_CENTER_RPM);

        nextRev = Math.max(nextRev,MIN_REV_RPM);
        nextRev = Math.min(nextRev, MAX_REV_RPM);

        return nextRev;
    }
}

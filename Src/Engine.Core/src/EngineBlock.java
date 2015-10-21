import busInterface.Engine_Out;
import busInterface.Public_In;

public class EngineBlock implements IEngineBlock{

    //Values calculated in Physical model of engine.xlsx
    public static final int MIN_REV_RPM = 500;
    public static final int MAX_REV_RPM = 7000;
    public static final int ENGINE_CENTER_RPM = 3000;
    public static final int MIN_TO_MAX_REV_TIME_MS =2000;
    public static final int BUS_SAMPLING_TIME_MS = 20;
    public static final int OPTIMAL_STEPPING = 65;

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

    public double GetRevOnePerSec(){return _currentRev / 60;}

    public void SetCurrentRev(double rev){
        rev = Math.max(rev, MIN_REV_RPM);
        rev = Math.min(rev, MAX_REV_RPM);

        _currentRev = rev;
    }

    public double GetTartgetRev(double targetPercentage) {
        targetPercentage = Math.max(targetPercentage,0);
        targetPercentage = Math.min(targetPercentage, 100);

        return MIN_REV_RPM + targetPercentage*(MAX_REV_RPM - MIN_REV_RPM)/100;
    }



    @Override
    public void Signal() {
        double targetRev = GetTartgetRev(_inBus.getGasPedalPercentage());

        double rev;
        if(targetRev >= _currentRev)
            rev = CalcNextPositiveRev(_currentRev, targetRev);
        else
            rev = CalcNextNegativeRev(_currentRev);

        SetCurrentRev(rev);
        //conversion
        _outBus.setEngineRevolution((int)GetRevOnePerSec());
    }

    public double CalcNextPositiveRev(double currentRev, double targetRev){
        double nextRev = currentRev + OPTIMAL_STEPPING * (currentRev / ENGINE_CENTER_RPM);

        nextRev = Math.min(nextRev, targetRev);

        return nextRev;
    }

    public double CalcNextNegativeRev(double currentRev){
        double nextRev = currentRev - OPTIMAL_STEPPING;

        nextRev = Math.max(nextRev, MIN_REV_RPM);

        return nextRev;
    }
}

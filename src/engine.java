/**
 * Created by CM on 2015.09.30..
 */
public class engine {

    protected double baseRPM=13.33;  //~800rpm
    protected double maxRPM=116.6; //~7000rpm
    protected double maxRange=maxRPM-baseRPM;
    protected double previousThrottle=0;
    protected double throttle;
    protected double actualRPM;

    public boolean isStarted(boolean start){
        if(start==true)
            return true;
        else
            return false;
    }


    protected boolean isThrottleValid(double throttle){
        if(throttle<100)
            return true;
        else
            return false;
    }

    protected boolean isThrottleScaleValid(double previousThrottle, double actualThrottle){
        if(Math.abs(actualThrottle-previousThrottle)<=10)
            return true;
        else
            return false;
    }

    protected boolean isSpeedUp(double previousThrottle, double actualThrottle){
        if(previousThrottle<actualThrottle){
            return true;
        }
        else{
            return false;
        }
    }



    public double generateRPM(boolean enginestatus, double Throttle){

        throttle=Throttle;

        if(isStarted(enginestatus)) {

            if(isThrottleValid(throttle)){

                if(isThrottleScaleValid(previousThrottle, throttle))
                {
                    if(isSpeedUp(previousThrottle, throttle))
                        return actualRPM=baseRPM + maxRange * (throttle / 100);
                    else
                        return actualRPM=baseRPM - maxRange * (throttle / 100);
                }
                else{

                    //ide kéne kidolgozni azt, hogy mivan, ha többet változik a gázpedál állása, mint 10%

                    return baseRPM;
                }
            }
            else {

               //ezt ki kéne dolgozni, hogy mi van ha invalid inputot kapunk
                return baseRPM;
            }
        }
        else{
            return 0;
        }

    }

    public engine(double baseRPM) {
        this.baseRPM = baseRPM;
    }


}

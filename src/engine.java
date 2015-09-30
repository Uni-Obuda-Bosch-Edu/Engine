/**
 * Created by CM on 2015.09.30..
 */
public class engine {

    protected double baseRPM=13.33;  //~800rpm
    protected double maxRPM=116.6; //~7000rpm
    protected double maxRange=maxRPM-baseRPM;

    public boolean isStarted(boolean start){
        if(start==true){
            return true;
        }
        else{
            return false;
        }
    }

    public double generateRPM(boolean enginestatus, double throttle){

        if(isStarted(enginestatus)) {
            if (throttle > 0) {

                return baseRPM + maxRange * (throttle / 100);
            } else {
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

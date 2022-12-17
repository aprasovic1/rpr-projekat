package ba.unsa.etf.rpr.rprprojekat.exceptions;

public class myException extends Exception{

    public myException(String msg, Exception reason){
        super(msg, reason);
    }

    public myException(String msg){
        super(msg);
    }
}

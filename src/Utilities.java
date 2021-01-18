import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utilities {
    //Log messges
    final static String PRINTER ="["+ generatedDateTime()+"] [PRINTER]: ";
    final static String STUDENT = "["+ generatedDateTime()+"] [STUDENT]: ";
    final static String PRINTING_SYSTEM = "["+ generatedDateTime()+"] [PRINTING SYSTEM]: ";
    final static String PAPER_TECHNICIAN = "["+ generatedDateTime()+"] [PAPER TECHNICIAN]: ";
    final static String TONER_TECHNICIAN = "["+ generatedDateTime()+"] [TONER TECHNICIAN]: ";

    public static String generatedDateTime(){
        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String logTime = formatter.format(date);
        return logTime;
    }
    //random duration time for thread sleeping
    public static long generateDuration(){

        return (long) (Math.random()*1000);
    }

}

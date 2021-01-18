import java.util.Random;

//Commen class for the technicians
public abstract class Technician extends Thread {
    protected Printer printer;

    public Technician(String technicianName, ThreadGroup threadGroup, Printer printer) {
        super(threadGroup, technicianName);
        this.printer = printer;
    }

}

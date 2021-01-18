import java.util.Random;

public class PaperTechnician extends Technician{

    public PaperTechnician(String technicianName,ThreadGroup threadGroup, Printer printer) {
        super(technicianName, threadGroup , printer);

    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            message(Utilities.PAPER_TECHNICIAN+" Requesting to refill the papers");
            ((LaserPrinter)printer).refillPaper();
            message(Utilities.PAPER_TECHNICIAN+"Printer Status: "+printer.toString());
            try {
                sleep(Utilities.generateDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void message(String paperTech) {
        System.out.println(paperTech);
    }


}

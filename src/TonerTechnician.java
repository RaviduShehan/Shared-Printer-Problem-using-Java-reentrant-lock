public class TonerTechnician extends Technician{
    public TonerTechnician(String technicianName, ThreadGroup threadGroup, Printer printer) {
        super(technicianName, threadGroup, printer);
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            message(Utilities.TONER_TECHNICIAN+" Requesting to replace the toner cartridge");
            ((LaserPrinter)printer).replaceTonerCartridge();
            message(Utilities.TONER_TECHNICIAN+"Printer Status: "+ printer.toString());
           try {
               sleep(Utilities.generateDuration());
           } catch (InterruptedException e) {
              e.printStackTrace();
          }
        }
    }

    private void message(String tonerTech) {
        System.out.println(tonerTech);
    }
}

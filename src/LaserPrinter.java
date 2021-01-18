import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LaserPrinter implements  ServicePrinter{
   //This methods consists with the resources in laserPrinter class and it is shared with the printing system

    //Initialized the variables and threadGroup
    private String printerID;
    private int paperStatus;
    private int tonerStatus;
    private int noOfDocumentsPrinted;
    private ThreadGroup users;


    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    public LaserPrinter(String printerID, ThreadGroup users){
        this.printerID= printerID;
        this.paperStatus= Full_Paper_Tray;
        this.tonerStatus= Full_Toner_Level;
        this.noOfDocumentsPrinted = 0;  //assigned the 0 for the initial No of documents printed
        this.users=users;
    }


    @Override
    public void replaceTonerCartridge() {
    lock.lock();

    ////Checking the need of replace toner in every 5 seconds of time
        try {
         while (tonerStatus > Minimum_Toner_Level){

              //check the printer status to ensure it has finished the process for all users
              if(isPrinterUsageFinish()){
                  message(Utilities.PRINTER + "Printer usage is finished. No need of replacing the toner cartridge");


              break;
             }else{
                  message(Utilities.PRINTER+"Printer Status: "+toString());
                  message(Utilities.PRINTER+"Toner has not reached it's minimum level, please wait to check again");

              condition.await(5, TimeUnit.SECONDS);

                }
        }
      //check and replacing the toner cartridge
         if(tonerStatus < Minimum_Toner_Level){
            tonerStatus = Full_Toner_Level;
             message(Utilities.PRINTER+"Replace the toner cartridge ");
             message(Utilities.PRINTER+"Printer Status: "+toString());

          }
             condition.signalAll();
         } catch (InterruptedException e) {
           // message(Utilities.PRINTER+"Printer Status: "+toString());
        }finally {
               lock.unlock();
        }

    }

    private void message(String printerMessgae) {
        System.out.println(printerMessgae);
    }

    @Override
    public void refillPaper() {
        lock.lock();

        try{
        //Checking the need of refilling papers in every 5 seconds of time
        while (paperStatus + SheetsPerPack > Full_Paper_Tray){
            if(isPrinterUsageFinish()){
                message(Utilities.PRINTER+"Printer usage is finished. No need of paper refilling");

              break;
            }else{
                 message(Utilities.PRINTER+"Printer Status: "+toString());
                 message(Utilities.PRINTER+"Paper refilling overloaded. Waiting to check");

                condition.await(5, TimeUnit.SECONDS);

            }
        }

        //Refilling according to the need
        if(paperStatus + SheetsPerPack <= Full_Paper_Tray){
            paperStatus = paperStatus + SheetsPerPack;
            message(Utilities.PRINTER+"Refilled the Papers");
            message(Utilities.PRINTER+"Printer Status: "+toString());

        }
        condition.signalAll();
        } catch (InterruptedException e) {
          //  message(Utilities.PRINTER+"Printer Status: "+toString());
        }finally {
            lock.unlock();
        }

    }



    @Override
    public void printDocument(Document document) {
     lock.lock();


        //Check the availablity of the Papers and toner
     try{
         message(Utilities.PRINTER+"Printer Status: "+toString());

        while ((paperStatus < document.getNumberOfPages() || tonerStatus < document.getNumberOfPages())){
            if(paperStatus < document.getNumberOfPages()){
                message(Utilities.PRINTER+"Insufficient papers, Wait till refilling...");
            }else if (tonerStatus <document.getNumberOfPages()){
                message(Utilities.PRINTER +"Insufficient Toner. Wait untill catridge replacing..");
            }else{
                message(Utilities.PRINTER+" Paper and Toner insufficient, Wait until they are available..");
            }
                condition.await();
        }

        //Print the documents if the sheets are avilable suffiently
        if(paperStatus >= document.getNumberOfPages() && tonerStatus >= document.getNumberOfPages()){
            paperStatus = paperStatus - document.getNumberOfPages();
            tonerStatus = tonerStatus - document.getNumberOfPages();
            noOfDocumentsPrinted++;
            message(Utilities.PRINTER+"Printed Document: "+document);
            message(Utilities.PRINTER+"Printer Status: "+toString());
        }

        condition.signalAll();
     } catch (InterruptedException e) {
         message(Utilities.PRINTER+"Printer Status: "+toString());
     }finally {
         lock.unlock();
     }

        }



    //To string method for represnt the current state of the printer
    @Override
    public String toString() {
        return "[printerID :" + printerID +
                ", Paper Status :" + paperStatus +
                ", Toner Status :" + tonerStatus +
                ", No of Documents Printed :" + noOfDocumentsPrinted + " ]";


    }
    private boolean isPrinterUsageFinish() {
        return users.activeCount() ==0;
    }

}

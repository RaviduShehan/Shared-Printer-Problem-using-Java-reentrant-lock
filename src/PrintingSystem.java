
public class PrintingSystem {
    public static void main(String[]args){

        //Creating the Thread Groups for students and technicians
        ThreadGroup students = new ThreadGroup("Students");
        message(Utilities.PRINTING_SYSTEM+"Created the ThreadGroup :" + students.getName());

        ThreadGroup technicians = new ThreadGroup("Technicians");
        message(Utilities.PRINTING_SYSTEM+"Created the ThreadGroup :" + technicians.getName());

        //Creat LaserPrinter
        LaserPrinter printer = new LaserPrinter("Printer01", students);
        message(Utilities.PRINTING_SYSTEM+"Printer Intialised");

        //Creat the thread for the Students

        Student student1 = new Student("Asanga", students, printer);
        message(Utilities.PRINTING_SYSTEM+"Initialised Student : " + student1.getName());

        Student student2 = new Student("Sanuka", students, printer);
        message(Utilities.PRINTING_SYSTEM+"Initialised Student : " + student2.getName());

        Student student3 = new Student("Gaveen", students, printer);
        message(Utilities.PRINTING_SYSTEM+"Initialised Student : " + student3.getName());

        Student student4 = new Student("Sarangi", students, printer);
        message(Utilities.PRINTING_SYSTEM+"Initialised Student : " + student4.getName());

        //Create the Technicians thread
        Technician paperTechnician = new PaperTechnician("Paper Technician", technicians, printer);
        message(Utilities.PRINTING_SYSTEM+"Paper Technician Initialised");

        Technician tonerTechnician = new TonerTechnician("Toner Technician", technicians, printer);
        message(Utilities.PRINTING_SYSTEM+"Torner Technician Initialised");

        //Start the threads in the process

        student1.start();
        message(Utilities.PRINTING_SYSTEM+"Started Student : " +student1.getName());

        student2.start();
        message(Utilities.PRINTING_SYSTEM+"Started Student : " +student2.getName());

        student3.start();
        message(Utilities.PRINTING_SYSTEM+"Started Student : " +student3.getName());

        student4.start();
        message(Utilities.PRINTING_SYSTEM+"Started Student : " +student4.getName());

        paperTechnician.start();
        message(Utilities.PRINTING_SYSTEM+"Paper technician Started");

        tonerTechnician.start();
        message(Utilities.PRINTING_SYSTEM+"Torner technician Started");


        try {
            student1.join();
            message(Utilities.PRINTING_SYSTEM+ student1.getName()+ " completed the process");

            student2.join();
            message(Utilities.PRINTING_SYSTEM+ student2.getName()+ " completed the process");

            student3.join();
            message(Utilities.PRINTING_SYSTEM+ student3.getName()+ " completed the process");

            student4.join();
            message(Utilities.PRINTING_SYSTEM+ student4.getName()+ " completed the process");

            paperTechnician.join();
            message(Utilities.PRINTING_SYSTEM+" Paper technician completed the process");

            tonerTechnician.join();
            message(Utilities.PRINTING_SYSTEM+"Torner technician completed the process");

            message(Utilities.PRINTING_SYSTEM+"Task completed. " +printer.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void message(String printerSystemMessge) {
        System.out.println(printerSystemMessge);
    }


}

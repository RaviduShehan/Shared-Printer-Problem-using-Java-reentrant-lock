import java.util.Random;




public class Student extends Thread {
    private Printer printer;

    public Student(String name, ThreadGroup threadGroup,Printer printer){
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            String documentName ="document_"+ (i +1);
            Document document = new Document(generateDocID(this.getName(),documentName),documentName,generateRandomPageCount());

            message(Utilities.STUDENT+"[" +this.getName()+"] Requesting to Print : " + document);
            printer.printDocument(document);

            try {
                sleep(Utilities.generateDuration()); //used the random timer here as well
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void message(String studentMessage) {
        System.out.println(studentMessage);
    }


    //Generate document ID according to Student name and the document name

    private String generateDocID(String studentName, String documentName) {
        return studentName + "-" + documentName;
    }

    //Generate random page count for the documents
    //Page count should be 1 to 20 ( with 1 & 20)
    private int generateRandomPageCount(){
         int pageCount = new Random().nextInt(19) + 1;
        return  pageCount;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class  BookManager {
    public static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Book> bookWaitList = new ArrayList<>();
    static String file;
    public static void addBookFromFile(String filename) throws IOException {
        file = filename;
        // tested
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        while(line != null){
            String[] bLine = line.split(",");
            String n = bLine[0];
            String g = bLine[1];
            String a = bLine[2];
            boolean isC = Boolean.parseBoolean(bLine[3]);
            int ID = Integer.parseInt(bLine[4]);
            String cTo = bLine[5];
            Book b = new Book(n, g, a, isC, ID, cTo);
            addToBookList(b);
            line = br.readLine();
        }
        br.close();
    }

    public static void addToBookList(Book book){
        // tested
        bookList.add(book);
    }
    public static void addToWaitList(Book book){
        //tested
        // add book to waitlist if checked out
        bookWaitList.add(book);
    }
    public static void removeFromWaitList(Book book){
        //tested
        // remove from waitlist if checked in
        bookWaitList.remove(book);
    }
    public static void checkOutBook(int inBookID, String userName){
        //tested
        // change the book's checkin status to true in bookList
        for (Book b : bookList){
            if (b.getBookID() == inBookID){
                b.isCheckedOut = true;
                b.checkedTo = userName;
                // if already checked out then call the addToWaitList method to add the book to waitList
                addToWaitList(b);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (Book b: bookList){
                writer.write(b.getTitle() + "," + b.getGenre() + "," + b.getAuthor() + "," + b.getCheckedOut() + "," +b.getBookID() + "," + b.getCheckedTo() + "\n");
            }
            writer.close();
        }catch(IOException ex) {
            System.out.println("Error.");
        }
    }
    public static void checkInBook(int inBookID){
        //tested
        // change the book's checkin status to false in bookList
        for (Book b : bookList){
            if (b.getBookID() == inBookID){
                b.isCheckedOut = false;
                b.checkedTo = "none";
                // if checked in and was in waitList then call the removeFromWaitList method
                removeFromWaitList(b);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (Book b: bookList){
                writer.write(b.getTitle() + "," + b.getGenre() + "," + b.getAuthor() + "," + b.getCheckedOut() + "," +b.getBookID() + "," + b.getCheckedTo() + "\n");
            }
            writer.close();
        }catch(IOException ex) {
            System.out.println("Error.");
        }
    }
}

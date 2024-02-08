// import pkgs
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
        
        bookList.add(book);
    }
    public static void addToWaitList(Book book){
    
        bookWaitList.add(book);
    }
    public static void removeFromWaitList(Book book){
        
        bookWaitList.remove(book);
    }
    public static void checkOutBook(int inBookID, String userName){
        
        for (Book b : bookList){
            if (b.getBookID() == inBookID){
                b.isCheckedOut = true;
                b.checkedTo = userName;
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
  
        for (Book b : bookList){
            if (b.getBookID() == inBookID){
                b.isCheckedOut = false;
                b.checkedTo = "none";
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

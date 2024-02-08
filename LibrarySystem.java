import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    public static void initializeFrame() {

        JFrame frame = new JFrame();
        frame.setTitle("XYZ Library");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame not resizable
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);

        // the box layout will be from top to bottom
        // testing a different layout
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome to XYZ Online Library System", JLabel.CENTER);

        // set font style and size
        label.setFont(new Font("Verdana", Font.BOLD, 20));

        JButton get_book_information = new JButton("Get Book Information");
        get_book_information.setBounds(800,800, 800, 800);
        get_book_information.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookLocation();
            }
        });
        JButton searchByKeyword = new JButton("Search Book by Keyword");
        searchByKeyword.setBounds(800,800, 800, 800);
        searchByKeyword.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame testFrame = new JFrame("BOOK SEARCH");
                testFrame.setSize(400, 100);
                testFrame.setResizable(false);
                testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                testFrame.setLocationRelativeTo(null);
                testFrame.setLayout(new FlowLayout());

                JButton enter = new JButton("Enter");
                JLabel text = new JLabel("Enter a Keyword:");
                JTextField textBook = new JTextField(20);
                testFrame.add(text);
                testFrame.add(textBook);
                //get the user input/keyword
                enter.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        if ("Enter".equals(command)){
                            //get the keyword
                            String keyword = textBook.getText();
                            //original frame disappears and the new one show up with results of the search based on input keyword
                            testFrame.dispose();
                            JFrame newFrame = new JFrame();
                            newFrame.setSize(900, 250);
                            newFrame.setTitle("Result");
                            newFrame.setResizable(false);
                            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            newFrame.setLayout(new FlowLayout());
                            newFrame.setLocationRelativeTo(null);

                            ArrayList<Book> books = new ArrayList<>();
                            for(Book b : BookManager.bookList){
                                if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                                    books.add(b);
                                } else if (b.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                                    books.add(b);
                                } else if (b.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                                    books.add(b);
                                }
                            }
                            if (books.size() > 0){
                                for (Book b : books){
                                    JLabel label = new JLabel(b.toString());
                                    newFrame.add(label);
                                }
                            } else {
                                JLabel label2 = new JLabel("No result found by search");
                                newFrame.add(label2);
                            }
                            newFrame.setVisible(true);
                            JButton back = new JButton("Return");
                            newFrame.add(back);
                            back.addActionListener(new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String command2 = e.getActionCommand();
                                    if ("Return".equals(command2)){
                                        newFrame.dispose();
                                    }
                                }
                            });
                        }
                    }
                });
                testFrame.add(enter);
                testFrame.setVisible(true);
            }
        });
        JButton check_in_button = new JButton("Check In Book");
        check_in_button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIn();
            }
        });
        JButton check_out_button = new JButton("Check Out Book");
        check_out_button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOut();
            }
        });
        // messing around with some code
        JButton viewLibraryButton = new JButton("View Library");
        viewLibraryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewLibrary();

            }
        });
        //exit button
        JButton exit = new JButton("Exit Program");
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //confirm before leave or exit the program
                String command = e.getActionCommand();
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Verdana", Font.PLAIN, 13)));
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Verdana", Font.PLAIN, 13)));
                if ("Exit Program".equals(command)) {
                    Object[] options = { "Confirm", "Cancel" };
                    int response = JOptionPane.showOptionDialog(frame, "Do you want to exit?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (response == 0) {
                        System.exit(0);
                    }
                }
            }
        });
        frame.add(label);
        frame.add(viewLibraryButton);
        frame.add(searchByKeyword);
        frame.add(get_book_information);
        frame.add(check_in_button);
        frame.add(check_out_button);
        frame.add(exit);
        frame.setVisible(true);
    }
    // use this method in all the button methods ?? searches through directory of books. utilize JComboBox
    public static void searchBooks(){
        // this opens a new window with a text input thing but you can't press enter on it because it doesnt do anything
        JFrame testFrame = new JFrame("BOOK SEARCH");
        testFrame.setSize(400, 100);
        testFrame.setResizable(false);
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testFrame.setLocationRelativeTo(null);
        testFrame.setLayout(new FlowLayout());

        JButton enter = new JButton("Enter");
        JLabel text = new JLabel("Enter a book ID:");
        JTextField textBook = new JTextField(20);
        testFrame.add(text);
        testFrame.add(textBook);
        enter.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if ("Enter".equals(command)){
                    String inputID = textBook.getText();
                    testFrame.dispose();
                    JFrame newFrame = new JFrame();
                    newFrame.setSize(900, 150);
                    newFrame.setTitle("Result");
                    newFrame.setResizable(false);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.setLayout(new FlowLayout());
                    newFrame.setLocationRelativeTo(null);

                    Book b2 = null;
                    for(Book b : BookManager.bookList){
                        if (String.valueOf(b.getBookID()).equals(inputID)) {
                            b2 = b;
                        }
                    }
                    if (b2 != null){
                        JLabel label1 = new JLabel(b2.toString());
                        newFrame.add(label1);
                    } else {
                        JLabel label = new JLabel("Invalid Book ID");
                        newFrame.add(label);
                    }
                    newFrame.setVisible(true);
                    JButton back = new JButton("Return");
                    newFrame.add(back);
                    back.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String command2 = e.getActionCommand();
                            if ("Return".equals(command2)){
                                newFrame.dispose();
                            }
                        }
                    });
                }
            }
        });
        testFrame.add(enter);
        testFrame.setVisible(true);
    }
    public static void bookLocation(){
        searchBooks();
    }
    public static void checkIn(){
        //searchBooks();
        JFrame testFrame = new JFrame("BOOK SEARCH");
        testFrame.setSize(400, 100);
        testFrame.setResizable(false);
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testFrame.setLocationRelativeTo(null);
        testFrame.setLayout(new FlowLayout());

        JButton enter = new JButton("Enter");
        JLabel text = new JLabel("Enter a book ID:");
        JTextField textBook = new JTextField(20);
        testFrame.add(text);
        testFrame.add(textBook);
        enter.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if ("Enter".equals(command)){
                    String inputID = textBook.getText();
                    testFrame.dispose();
                    JFrame newFrame = new JFrame();
                    newFrame.setSize(900, 150);
                    newFrame.setTitle("Result");
                    newFrame.setResizable(false);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.setLayout(new FlowLayout());
                    newFrame.setLocationRelativeTo(null);

                    Book b2 = null;
                    for(Book b : BookManager.bookList){
                        if (String.valueOf(b.getBookID()).equals(inputID)) {
                            b2 = b;
                        }
                    }
                    if (b2 != null){
                        BookManager.checkInBook(b2.getBookID());
                        JLabel label1 = new JLabel("Successfully check in the following book:");
                        JLabel label2 = new JLabel(b2.toString());
                        newFrame.add(label1);
                        newFrame.add(label2);

                    } else {
                        JLabel label = new JLabel("Invalid Book ID");
                        newFrame.add(label);
                    }
                    newFrame.setVisible(true);
                    JButton back = new JButton("Return");
                    newFrame.add(back);
                    back.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String command2 = e.getActionCommand();
                            if ("Return".equals(command2)){
                                newFrame.dispose();
                            }
                        }
                    });
                }
            }
        });
        testFrame.add(enter);
        testFrame.setVisible(true);
    }
    public static void checkOut(){
        //searchBooks();
        JFrame testFrame = new JFrame("BOOK SEARCH");
        testFrame.setSize(400, 100);
        testFrame.setResizable(false);
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testFrame.setLocationRelativeTo(null);
        testFrame.setLayout(new FlowLayout());

        JButton enter = new JButton("Enter");
        JLabel text = new JLabel("Enter a book ID:");
        JTextField textBook = new JTextField(20);
        testFrame.add(text);
        testFrame.add(textBook);
        enter.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if ("Enter".equals(command)){
                    String inputID = textBook.getText();
                    testFrame.dispose();

                    JFrame userFrame = new JFrame();
                    userFrame.setSize(900, 150);
                    userFrame.setTitle("User Info");
                    userFrame.setResizable(false);
                    userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    userFrame.setLayout(new FlowLayout());
                    userFrame.setLocationRelativeTo(null);

                    JButton enterNext = new JButton("Enter");
                    JLabel user = new JLabel("Enter your username:");
                    JTextField userText = new JTextField(20);
                    userFrame.add(user);
                    userFrame.add(enterNext);//TODO flag
                    userFrame.add(userText);
                    userFrame.setVisible(true);
                    enterNext.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String cmd = e.getActionCommand();
                            if ("Enter".equals(cmd)){
                                String userIn = userText.getText();
                                userFrame.dispose();

                                JFrame newFrame = new JFrame();
                                newFrame.setSize(900, 150);
                                newFrame.setTitle("Result");
                                newFrame.setResizable(false);
                                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                newFrame.setLayout(new FlowLayout());
                                newFrame.setLocationRelativeTo(null);

                                if(User.checkAcc(userIn)){
                                    Book b2 = null;
                                    for(Book b : BookManager.bookList){
                                        if (String.valueOf(b.getBookID()).equals(inputID)) {
                                            b2 = b;
                                        }
                                    }
                                    if (b2 != null){
                                        BookManager.checkOutBook(b2.getBookID(), userIn);

                                        JLabel label1 = new JLabel("Successfully check out the following book:");
                                        JLabel label2 = new JLabel(b2.toString());
                                        newFrame.add(label1);
                                        newFrame.add(label2);

                                    } else {
                                        JLabel label = new JLabel("Invalid Book ID");
                                        newFrame.add(label);
                                    }
                                }
                                else{
                                    JLabel l = new JLabel("Invalid username");
                                    newFrame.add(l);
                                }
                                newFrame.setVisible(true);
                            }
                        }
                    });
                }
            }
        });
        testFrame.add(enter);
        testFrame.setVisible(true);
    }
    public static void viewLibrary(){
        JFrame library = new JFrame();
        library.setTitle("Library");
        library.setSize(1000, 500);
        library.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        library.setLayout(new FlowLayout());
        library.getContentPane().setBackground(Color.LIGHT_GRAY);
        library.setLocationRelativeTo(null);
        /**
         we should change PrintBookList so that it returns something because idk how else to print it here
         */

        /**
         String printed = "";
         for (Book b : BookManager.bookList){
         printed += "<html>" + b.toString() +"</html>"; // + "<br/>";
         }
         //JLabel text = new JLabel(BookManager.printBookList());
         JLabel text = new JLabel(printed);
         library.add(text);
         */
        for(Book b : BookManager.bookList){
            JLabel label = new JLabel(b.toString());
            library.add(label);

        }
        library.setVisible(true);
        JButton back = new JButton("Return");
        library.add(back);
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command2 = e.getActionCommand();
                if ("Return".equals(command2)){
                    library.dispose();
                }
            }
        });
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class User {
    private static String foldername;
    private static String filename;
    public static boolean createfolder(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            }else {
                return false;
            }
        }catch(Exception e) {
        }return false;
    }

    public static boolean createaccount(String name) throws IOException{
        boolean flag = false;
        filename = "librarySystem/"+ name + ".txt";
        File account = new File(filename);

        if (!account.exists()) {
            account.createNewFile();
            flag = true;
        }
        return flag;
    }

    public static boolean checkAcc (String s){
        String f = "librarySystem/"+ s + ".txt";
        File acc = new File(f);
        if(acc.exists()){
            return true;
        }
        else return false;
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        foldername = "librarySystem";
        if (createfolder(foldername)) {
            System.out.println("System created");
        } else {
            System.out.println("System exist");
        }
        JFrame frame = new JFrame();
        frame.setTitle("XYZ Library");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to XYZ Online Library System", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 20));

        JButton logIn = new JButton("Log in");
        logIn.setBounds(800,800, 800, 800);
        logIn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame testFrame = new JFrame("LOG IN");
                testFrame.setSize(400, 150);
                testFrame.setResizable(false);
                testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                testFrame.setLocationRelativeTo(null);
                testFrame.setLayout(new FlowLayout());

                JButton enter = new JButton("Enter");
                JLabel text = new JLabel("Enter username:");
                JTextField textBook = new JTextField(20);
                testFrame.add(text);
                testFrame.add(textBook);
                JLabel text2 = new JLabel("Enter password:");
                JTextField textBook2 = new JTextField(20);
                testFrame.add(text2);
                testFrame.add(textBook2);
                testFrame.add(enter);
                enter.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        if ("Enter".equals(command)){
                            String acc_name = textBook.getText();
                            String acc_pw = textBook2.getText();

                            File acc = new File("librarySystem/"+ acc_name + ".txt");
                            try {
                                BufferedReader readTxt1;
                                readTxt1 = new BufferedReader(new FileReader(acc));
                                String str1 = "";
                                String text1 = "";
                                while ((text1 = readTxt1.readLine()) != null) {
                                    str1 += text1;
                                }
                                String[] array1 = str1.split(",");
                                if (acc.exists()) {
                                    String pwinrecord = "Password: " + acc_pw;
                                    if (array1[1].equals(pwinrecord)) {
                                        System.out.println("Access Granted!");
                                        BookManager.addBookFromFile("book.txt");
                                        LibrarySystem.initializeFrame();
                                        testFrame.dispose();
                                        frame.dispose();

                                    }else {
                                        testFrame.dispose();
                                        JFrame newFrame = new JFrame();
                                        newFrame.setSize(400, 150);
                                        newFrame.setTitle("Result");
                                        newFrame.setResizable(false);
                                        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        newFrame.setLayout(new FlowLayout());
                                        newFrame.setLocationRelativeTo(null);
                                        JLabel label = new JLabel("Invalid password. Failed to log in.");
                                        newFrame.add(label);
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
                            }catch (IOException ext){
                                if (ext instanceof FileNotFoundException){
                                    testFrame.dispose();
                                    JFrame newFrame = new JFrame();
                                    newFrame.setSize(400, 150);
                                    newFrame.setTitle("Result");
                                    newFrame.setResizable(false);
                                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    newFrame.setLayout(new FlowLayout());
                                    newFrame.setLocationRelativeTo(null);
                                    JLabel label = new JLabel("Account does not exist.");
                                    newFrame.add(label);
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
                                } else {
                                    System.err.println("Exception " + ext);
                                }
                            }
                        }
                    }
                });
                testFrame.setVisible(true);
            }
        });

        JButton signUp = new JButton("Sign Up");
        frame.add(signUp);
        signUp.setBounds(800,800, 800, 800);
        signUp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame testFrame = new JFrame("SIGN UP");
                testFrame.setSize(400, 150);
                testFrame.setResizable(false);
                testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                testFrame.setLocationRelativeTo(null);
                testFrame.setLayout(new FlowLayout());

                JButton enter = new JButton("Enter");
                JLabel text = new JLabel("Enter username:");
                JTextField textBook = new JTextField(20);
                testFrame.add(text);
                testFrame.add(textBook);
                JLabel text2 = new JLabel("Enter password:");
                JTextField textBook2 = new JTextField(20);
                testFrame.add(text2);
                testFrame.add(textBook2);
                testFrame.add(enter);
                enter.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        if ("Enter".equals(command)){
                            String new_name = textBook.getText();
                            String pw = textBook2.getText();
                            try {
                                if (createaccount(new_name)) {
                                    try {
                                        FileWriter writer = new FileWriter("librarySystem/"+ new_name +".txt");
                                        writer.write("Username: " + new_name + ",");
                                        writer.write("\nPassword: "+ pw + ",");
                                        //writer.flush();
                                        writer.close();

                                    }catch(IOException ex) {
                                        System.out.println("Error.");
                                    }

                                    testFrame.dispose();
                                    JFrame newFrame = new JFrame();
                                    newFrame.setSize(400, 150);
                                    newFrame.setTitle("Result");
                                    newFrame.setResizable(false);
                                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    newFrame.setLayout(new FlowLayout());
                                    newFrame.setLocationRelativeTo(null);
                                    JLabel label = new JLabel("Account Created Successfully!");
                                    newFrame.add(label);
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
                                } else {
                                    testFrame.dispose();
                                    JFrame newFrame = new JFrame();
                                    newFrame.setSize(400, 150);
                                    newFrame.setTitle("Result");
                                    newFrame.setResizable(false);
                                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    newFrame.setLayout(new FlowLayout());
                                    newFrame.setLocationRelativeTo(null);
                                    JLabel label = new JLabel("Account already exists.");
                                    newFrame.add(label);
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
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                testFrame.setVisible(true);
            }
        });
        frame.add(logIn);
        frame.add(signUp);
        frame.setVisible(true);

    }
}
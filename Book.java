public class Book {
    private String title;
    boolean isCheckedOut;
    private String genre;
    private String author;
    private int bookID;
    String checkedTo;

    public Book(String _title, String _genre, String _author, boolean isCheckedOut_, int _bookID, String checkedTo) {
        this.title = _title;
        this.genre = _genre;
        this.author = _author;
        this.isCheckedOut = isCheckedOut_;
        this.bookID = _bookID;
        this.checkedTo = checkedTo;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getCheckedOut(){
        return this.isCheckedOut;
    }

    public String getCheckedOutSt() {
        if(this.isCheckedOut == false){
            return "not checked out";
        }
        else{
            return "checked out";
        }
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getBookID() {
        return this.bookID;
    }
    public String getCheckedTo(){
        return this.checkedTo;
    }

    @Override
    public String toString(){
        return("<html>" + "<B>" + "Title: " + "</B>" + this.getTitle() + "<B>"+" Genre: "+ "</B>" + this.getGenre() + "<B>" + " Author: " + "</B>" + this.getAuthor() + "<B>" + " Checkin Status: " + "</B>" + this.getCheckedOutSt() + "<B>" + " Book ID: " +"</B>" + this.getBookID()+ "<B>" + " Checked to: " + "</B>" + this.getCheckedTo() +"</html>");
    }
}


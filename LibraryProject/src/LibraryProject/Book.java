package LibraryProject;


public class Book {
    String id;
    String fname;
    String lastname;
    String title;
    String genre;
    LinkedListQueue waiting;

    //Constructor
    Book( String id , String fname, String lastname,String title,String genre)
    {
        this.id = id;
        this.fname = fname;
        this.lastname = lastname;
        this.title = title;
        this.genre = genre;
        waiting = new LinkedListQueue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //Display Book Info
    public void Display()
    {
        System.out.print(id + "--");
        System.out.print(fname+ "--");
        System.out.print(lastname+ "--");
        System.out.print(title+ "--");
        System.out.println(genre);
    }
}

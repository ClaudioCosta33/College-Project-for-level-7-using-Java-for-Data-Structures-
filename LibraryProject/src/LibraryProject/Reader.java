package LibraryProject;

import java.util.LinkedList;
import java.util.List;

public class Reader {
    String id;
    String firstname;
    String lastname;
    String address;
    List<Book> borrowed;
    //Constructor
    Reader( String id , String fname, String lastname,String address)
    {
        this.id = id;
        this.firstname = fname;
        this.lastname = lastname;
        this.address = address;
        borrowed = new LinkedList<>();
    }
    //Display Info
    public void DisplayReaderInfo()
    {
        System.out.print(id + "--");
        System.out.print(firstname+ "--");
        System.out.print(lastname+ "--");
        System.out.println(address);
    }
    //Display Borrowed Books by reader
    public void DisplayBorrowed()
    {
        for(int i=0;i<borrowed.size();i++)
        {
            System.out.println(borrowed.get(i).title);
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

package LibraryProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Menu {

    private List<Book> listB;//Book list
    private List<Reader> listP;//Reader List

    Menu()
    {
        listB = new LinkedList<>();
        listP = new LinkedList<>();
    }

    //Sort Reader with Last Name
    List<Reader> SortReader(List<Reader> listP)
    {
        for(int i=0;i<listP.size();i++)
        {
            for(int j=i+1;j<listP.size();j++)
            {
                if(listP.get(i).getLastname().compareTo(listP.get(j).getLastname()) > 0)
                {
                    Reader temp = listP.get(i);
                    listP.set(i,listP.get(j));
                    listP.set(j,temp);
                }
            }
        }
        return listP;
    }

    //Sort Reader with author Last Name
    List<Book> SortBooks(List<Book> listB)
    {
        for(int i=0;i<listB.size();i++)
        {
            for(int j=i+1;j<listB.size();j++)
            {
                if(listB.get(i).getlastname().compareTo(listB.get(j).getlastname()) > 0)
                {
                    Book temp = listB.get(i);
                    listB.set(i,listB.get(j));
                    listB.set(j,temp);
                }
            }
        }
        return listB;
    }

    //Search a specific book in List and return index
    public int SearchBook(List<Book> listB,String name)
    {
        for(int i=0; i < listB.size();i++)
        {
            if(listB.get(i).title.equals(name))
                return i;
        }
        //book not found
        return -1;
    }

    //Search a specific reader in List and return index
    public int SearchReader(List<Reader> listP,String name)
    {
        for(int i=0; i < listP.size();i++)
        {
            if(listP.get(i).lastname.equals(name))
                return i;
        }
        //reader not found
        return -1;
    }

    //Displaying Options
    void DisplayMenu()
    {
        System.out.println("Press 1 to Search for a book");
        System.out.println("Press 2 to Display Books");
        System.out.println("Press 3 to Search for a Reader");
        System.out.println("Press 4 to Display Readers");
        System.out.println("Press 5 Borrow a Book");
        System.out.println("Press 6 to Return a Borrowed Book");
        System.out.println("Press 7 to List Borrowed Books");
    }
   //Initilaize the Library
void begin() {
    //File readbooks
    try (BufferedReader br = Files.newBufferedReader(Paths.get("Books.csv"))) {

        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");//split on basis of ','
            Book temp = new Book(columns[0], columns[1], columns[2], columns[3], columns[4]);

            listB.add(temp);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    //===================================================================
    //File readReader
    try (BufferedReader br = Files.newBufferedReader(Paths.get("Readers.csv"))) {

        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");//split on basis of ','
            Reader temp = new Reader(columns[0], columns[1], columns[2], columns[3]);

            listP.add(temp);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    //===================================================================

    //Management System in loop
    while (true) {
        DisplayMenu();//Menu Display
        Scanner s = new Scanner(System.in);
        int menuOptions = s.nextInt();//Input option from menu
        //===================================================================


        if (menuOptions == 1) {
            System.out.println("please Enter Book title");
            Scanner sc = new Scanner(System.in);
            String search = sc.nextLine();//book title input
            if (SearchBook(listB, search) != -1) {
                System.out.println("Book Found");
            }
            else {
                System.out.println("No Book Found");
            }
        }
        //===================================================================

        if (menuOptions == 2) {
            System.out.println("\nList of Books in sorted order are\n");

            listB = SortBooks(listB);//sort book on basis of Authorlast name
            for (Book book : listB) {
                book.Display();
            }
        }
        //===================================================================

        if (menuOptions == 3) {
            System.out.println("please Enter Readers Lastname");
            Scanner sc = new Scanner(System.in);
            String search = sc.nextLine();//reader Search input
            if (SearchReader(listP, search) != -1) {
                System.out.println("Reader Found");
            }
            else {
                System.out.println("No Reader Found");
            }
        }
        //===================================================================

        if (menuOptions == 4) {

            System.out.println("\nList of Readers in sorted order are\n");
            listP = SortReader(listP);//sort book on basis of last name

            for (Reader person : listP) {
                person.DisplayReaderInfo();
            }
        }

        //===================================================================

        if (menuOptions == 5) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter Readers Lastname");
                String pr = sc.nextLine();//readers lastname to add book

                int i = SearchReader(listP, pr);
                if (i != -1) {
                    System.out.println("Please enter Book title to borrow");
                    String bb = sc.nextLine();//book title input
                    int index = SearchBook(listB, bb);//index of book to add

                    if (index != -1) {
                        if (listB.get(index).waiting.isEmpty()) {
                            listP.get(i).borrowed.add(listB.get(index));//add book in readers borrowed list
                        }
                        if(listB.get(index).waiting.getQueueSize()>0)
                            listB.get(index).waiting.print_Queue();//print last person in queue
                        listB.get(index).waiting.enqueue(listP.get(i));//enqueue reader in book queue
                    } else {
                        System.out.println("No book found");
                    }
                } else {
                    System.out.println("Please Enter Valid Name");
                }
        }

        //===================================================================
        if (menuOptions == 6) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter Readers name");
                String pr = sc.nextLine();//reader last name input

                int i = SearchReader(listP, pr);

                if (i != -1) {
                    System.out.println("Please enter Book title to return");
                    String bb = sc.nextLine();//book title input
                    int index = SearchBook(listB, bb);

                    if (index != -1) {
                        if (listB.get(index).waiting.getFront().equals(listP.get(i).lastname)) {//check if book title is in last of queue
                            listB.get(index).waiting.dequeue();
                            if (listB.get(index).waiting.getQueueSize() != 0) {
                                System.out.println(listB.get(index).waiting.getFront());//print lastperson in queue
                            } else {
                                System.out.println("Waiting List Empty");
                            }
                            listP.get(i).borrowed.remove(listB.get(index));//remove book from readers borrowed list
                        } else {
                            int w = listB.get(index).waiting.QueueIndex(listP.get(i).lastname);
                            if (w != -1) {
                                System.out.println("You are in " + w + "in the waiting order");
                            } else {
                                System.out.println("You currently have these books to return from");
                                listP.get(i).DisplayBorrowed();
                            }
                        }
                    } else {
                        System.out.println("No book found");
                    }
                }
        }
        //===================================================================
        if (menuOptions == 7) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter Readers name");
            String pr = sc.nextLine();//reader last name input

            int i = SearchReader(listP, pr);

            if (i != -1) {
                System.out.println("The list of borrowed book are");
                if (listP.get(i).borrowed.size() > 0) {
                    System.out.println("id--FirstName--LastName--Title--Genre");
                    for (Book b : listP.get(i).borrowed) {
                        b.Display();//display books borrowed by reader
                    }
                }
                else {
                    System.out.println("No borrowed Books");
                }
            } else {
                System.out.println("No book found");
            }
        }

        System.out.println("\nPress any key for Main Menu\nPress 0 to exit");
        int options= s.nextInt();
        if (options == 0)//option 0 to break loop
        {
            break;
        }
    }
}
}

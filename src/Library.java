import java.util.ArrayList;

public class Library implements Searchable{
    static ArrayList<LibraryItem> catalog = new ArrayList<>();
    public LibraryItem searchByTitle(String title) {
        for (LibraryItem i : catalog) {
            if (i.getTitle().equals(title)) {
                return i;
            }
        }
        return null;
    }
    public static void addItem(LibraryItem item) {
        catalog.add(item);
    }
    public static void removeItem(LibraryItem item) {
        catalog.remove(item);
    }
    public static ArrayList<LibraryItem> getCatalog() {
        return catalog;
    }

    public LibraryItem searchByISBN(String isbn) {
        for (LibraryItem i : catalog) {
            if(i.getIsbn().equals(isbn)){
                return i;
            }

        }
        return null;
    }
    @Override
    public LibraryItem searchByID(int id) {
        for (LibraryItem i : catalog) {
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }
}

import java.util.ArrayList;

public class Book extends LibraryItem{
    public String genre;
    public int totalPages;
    public Book(String title, String author, String isbn, boolean isAvailable,int id,String borrowedBy, String genre, int totalPages) {
        super(title,author,isbn,isAvailable,borrowedBy,id);
        this.genre = genre;
        this.totalPages = totalPages;

    }
    void checkOut(Member member) {
                this.setIsAvailable(false);
                System.out.println("Kitap başarıyla ödünç alındı!");


    }
    void returnItem(Member member) {
            this.setIsAvailable(true);
            System.out.println("Kitap başarıyla rafa konuldu.");

    }

}

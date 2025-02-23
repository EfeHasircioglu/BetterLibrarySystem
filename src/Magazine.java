
public class Magazine extends LibraryItem {
    public int issueDate;
    public Magazine(String title, String author, String isbn, boolean isAvailable,String borrowedBy, int id,int issueDate) {
        super(title, author, isbn, isAvailable,borrowedBy,id);
        this.issueDate = issueDate;
    }
    //dergiyi ödünç alma, diğer bütün kontroller mainde yapılacak.
    void checkOut(Member member) {
                this.setIsAvailable(false);
                System.out.println("Dergi başarıyla ödünç alındı!");

    }
    void returnItem(Member member) {
            this.setIsAvailable(false);
            System.out.println("Dergi rafa geri konulmuştur.");
        }
    }


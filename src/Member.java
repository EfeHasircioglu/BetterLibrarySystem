import java.util.ArrayList;

public class Member {
    private String name;
    private String memberID;
    private String password;
    public int maxBorrowLimit = 3;
    ArrayList<LibraryItem> borrowedItems = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();
    ArrayList<Librarian> librarians = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Member(String name, String memberId,String password) {
        this.name = name;
        this.memberID = memberId;
        this.password = password;
    }


    void borrowItem(LibraryItem item) {
        if (item.getIsAvailable()) {
            if (borrowedItems.size() < maxBorrowLimit) {
                borrowedItems.add(item);
                item.checkOut(this);
                System.out.println(item.getTitle() + " adlı, "+ item.getId() + "ID'li ürün ödünç alındı!");
            } else {
                System.out.println("Ödünç alma limitiniz doldu!");
            }
        } else {
            System.out.println("Ürün rafta değil.");
        }
    }

    void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            borrowedItems.remove(item);
            item.returnItem(this);
        } else {
            System.out.println("Ürün başkası tarafından ödünç alınmış.");
        }

    }

    void viewBorrowedItems() {
        for (LibraryItem i : borrowedItems) {
            System.out.println("Başlık: " + i.getTitle() + "\t" + "Yazar: " + i.getAuthor() + "\t" + "ISBN: " + i.getIsbn() + "\t" + "Uygun mu: " + i.getIsAvailable()+ "ID: " + i.getId());
        }
    }
}

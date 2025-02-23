import java.util.ArrayList;
import java.util.Objects;

public class Librarian extends Member{
    public Librarian(String name,String memberId,String password) {
        super(name,memberId,password);
    }
//TODO: daha sonra kullanıcı şifresi değiştirme özelliği getirilebilir!
    void addItem(LibraryItem item){
        for (LibraryItem items : Library.getCatalog()){
            if (items.getId()==item.getId()){
            System.out.println("Aynı ID'ye sahip başka bir kitap bulunmakta!");
            return;
            }
        }
        Library.addItem(item);
        if (item instanceof Book){
            System.out.println("Kitap başarıyla eklendi.");
        } else if (item instanceof Magazine){
            System.out.println("Dergi başarıyla eklendi.");
        }
    }
    public void addMember(Member member) {
        for (int i = 0;i< members.size();i++){
            if (members.get(i).getMemberID()== member.getMemberID()){
                System.out.println("Aynı ID'ye sahip başka bir üye bulunmakta!");
                return;
            }
        }
        members.add(member);
        System.out.println("Üye başarıyla eklendi.");
        System.out.println("Üye ID'si: " + member.getMemberID());
    }
    public void addLibrarian(Librarian librarian) {
        for (Librarian value : librarians) {
            if (Objects.equals(value.getMemberID(), librarian.getMemberID())) {
                System.out.println("Hata! Lütfen tekrar deneyin");
                return;
            }
        }
        Librarian.members.add(librarian);
        System.out.println("Kütüphaneci hesabı başarıyla eklendi.");
        System.out.println("Kütüphaneci hesabı ID'si: " + librarian.getMemberID());
    }
    public void removeMember(Member member) {
        members.remove(member);
    }
    public void removeLibrarian(Librarian librarian) {
        librarians.remove(librarian);
    }
    void removeItem(LibraryItem item){
        Library.removeItem(item);
        if (item instanceof Book){
            System.out.println("Kitap başarıyla silindi.");
        }
        else if (item instanceof Magazine){
            System.out.println("Dergi başarıyla silindi.");
        }
    }
    void listAllItems(){
        for (LibraryItem item : Library.getCatalog()){
            if (item == null){
                System.out.println("Kayıtlı kitap bulunamadı.");
            }
            if (item instanceof Book){
                System.out.println("Ürün türü: Kitap");
            }
            else if (item instanceof Magazine){
                System.out.println("Ürün türü: Dergi");
            }
            System.out.println("Başlık: " + item.getTitle());
            System.out.println("Yazar: " + item.getAuthor());
            System.out.println("ISBN: " + item.getIsbn());
            System.out.println("Kütüphane ID: " + item.getId());
            System.out.println("Raf durumu: " + item.getIsAvailable());
            if (item.getBorrowedBy() != null){
                System.out.println("Ödünç alan: " + item.getBorrowedBy());
            }
            System.out.println("---");
        }
    }
    void showAllMembers(){
        try {
            for (int i = 0; i < Member.members.size(); i++) {

                System.out.println("Kullanıcı ID: " + members.get(i).getMemberID());
                System.out.println("Kullanıcı Adı: " + members.get(i).getName());
                if (members.get(i) instanceof Librarian) {
                    System.out.println("Hesap Türü: Kütüphaneci hesabı");
                } else {
                    System.out.println("Hesap türü: Üye hesabı");
                }
                System.out.println("---");

            }
        }
        catch (Exception e){
            if (e instanceof IndexOutOfBoundsException){
                System.out.println("Hata! Muhtemelen kayıtlı üye yok (kayıtlı üye listesi boş.)");
            }
            else {
                System.out.println("Hata! " + e.getMessage());
            }
        }
    }
}

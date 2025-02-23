import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Member loggedMember = null;
            MemberManager memberManager = new MemberManager();
            System.out.println("Kütüphane yönetim sistemine hoş geldiniz.");
            System.out.println("Giriş Yap");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Kullanıcı ID: ");
                String id = scanner.nextLine().trim();
                System.out.println("Şifre: ");
                String passwd = scanner.nextLine();
                String hashedPasswd = PasswordHasher.hashPassword(passwd);
                System.out.println();
                loggedMember = memberManager.Login(id, hashedPasswd);
                if (loggedMember == null) {
                    System.out.println("Giriş başarısız, lütfen tekrar deneyin.");
                } else if (loggedMember instanceof Librarian) {
                    System.out.println("Kütüphaneci olarak giriş yapıldı.");
                    librarianMenu((Librarian) loggedMember);
                } else {
                    memberMenu(loggedMember);
                }
            }
        }catch (Exception e) {
            System.out.println("Hata!"+e.getMessage());
        }
    }
    public static void librarianMenu(Librarian librarian){
        Searchable searchMethod = new Library();
        libMenuLoop:
        while (true){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Yapabileceğiniz işlemler: \n 1. Yeni bir ürün ekleyin (kitap veya dergi) \n 2. Bir ürün kaldırın \n 3. Katalogdaki bütün ürünleri görüntüleyin \n 4. Bir ürünü arayın \n 5. Hesap ekleyin \n 6. Hesap silin \n 7. Hesap ara \n 8. Bütün üyeleri göster \n 9. Kullanıcı şifresi değiştir \n 10. Çıkış yap");
        int selected = scanner.nextInt();
        if (selected == 1) {
            innerLoop:
            while (true){

            System.out.println("Kitap eklemek istiyorsanız 1'e, Dergi eklemek istiyorsanız 2'ye, çıkmak için 3'e basın.");
            int selectedAdd = scanner.nextInt();
            scanner.nextLine(); // arta kalan satırı harcıyor
            if (selectedAdd == 1) {
                try {
                    System.out.println("Kitap ekleniyor.");
                    System.out.println("Başlık: ");
                    String name = scanner.nextLine();
                    System.out.println("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.println("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); //arta kalan satırı harcıyor
                    System.out.println("Tür: ");
                    scanner.next();
                    String genre = scanner.nextLine();
                    System.out.println("Sayfa miktarı: ");
                    int pages = scanner.nextInt();

                    Book book = new Book(name, author, isbn, true, id, "Hiç kimse", genre, pages);
                    librarian.addItem(book);
                }
                catch (Exception e) {
                    System.out.println("Hata: " + e.getMessage());
                }
            } else if (selectedAdd == 2) {
                try {
                    System.out.println("Dergi ekleniyor.");
                    System.out.println("Başlık: ");
                    String name = scanner.nextLine();
                    System.out.println("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.println("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    System.out.println("Yayınlanma tarihi: ");
                    int issueDate = scanner.nextInt();
                    Magazine magazine = new Magazine(name, author, isbn, false, "Hiç kimse", id, issueDate);
                    librarian.addItem(magazine);
                } catch (Exception e) {
                    System.out.println("Hata: " + e.getMessage());
                }

            }
            else if (selectedAdd == 3){
                break innerLoop;
            }
        }


        }
        else if (selected == 2){
            System.out.println("Silmek istediğiniz ürünün ID'sini girin: ");
            int id = scanner.nextInt();
            LibraryItem itemToDelete = searchMethod.searchByID(id);
            if (itemToDelete != null) {
                librarian.removeItem(itemToDelete);
            }
            else{
                System.out.println("Ürün bulunamadı.");
            }
        }
        else if (selected == 3) {
        librarian.listAllItems();
        }
        else if (selected == 4){
            searchLoop:
            while (true){
            System.out.println("ISBN ile aramak istiyorsanız 1'e, ID ile aramak istiyorsanız 2'ye, İsim ile aramak istiyorsanız 3'e basın. Çıkmak için 4'e basın.");
            int selectedSearch = scanner.nextInt();
            scanner.nextLine();
            if (selectedSearch == 1){
                System.out.println("ISBN girin: ");
                String isbn = scanner.nextLine();
                LibraryItem itemToSearch = searchMethod.searchByISBN(isbn);
                if (itemToSearch != null){
                    if (itemToSearch instanceof Magazine){
                        System.out.println("Ürün türü: Dergi");
                    } else if (itemToSearch instanceof Book){
                        System.out.println("Ürün türü: Kitap");
                    }
                    System.out.println("Ürün bulundu");
                    System.out.println("Başlık: "+itemToSearch.getTitle());
                    System.out.println("Yazar: "+itemToSearch.getAuthor());
                    System.out.println("ISBN: "+itemToSearch.getIsbn());
                    System.out.println("Kütüphane ID: "+itemToSearch.getId());
                    System.out.println("Raf durumu: " + itemToSearch.getIsAvailable());
                    if (!itemToSearch.getIsAvailable()){
                        System.out.println("Ödünç alan: " + itemToSearch.getBorrowedBy());
                    }


                }
                else {
                    System.out.println("Ürün bulunamadı!");
                }
            }
                else if (selectedSearch == 2){
                    System.out.println("ID girin: ");
                    int id = scanner.nextInt();
                    LibraryItem itemToSearch = searchMethod.searchByID(id);
                    if (itemToSearch != null){
                        System.out.println("Ürün bulundu");
                        System.out.println("Başlık: "+itemToSearch.getTitle());
                        System.out.println("Yazar: "+itemToSearch.getAuthor());
                        System.out.println("ISBN: "+itemToSearch.getIsbn());
                        System.out.println("Kütüphane ID: "+itemToSearch.getId());
                        System.out.println("Raf durumu: " + itemToSearch.getIsAvailable());
                        if (!itemToSearch.getIsAvailable()){
                            System.out.println("Ödünç alan: " + itemToSearch.getBorrowedBy());
                        }                    }
                    else {
                        System.out.println("Ürün bulunamadı!");
                    }
                }
            else if (selectedSearch == 3){
                System.out.println("İsim girin: ");
                String name = scanner.nextLine();
                LibraryItem itemToSearch = searchMethod.searchByTitle(name);
                if (itemToSearch != null){
                    System.out.println("Ürün bulundu");
                    System.out.println("Başlık: "+itemToSearch.getTitle());
                    System.out.println("Yazar: "+itemToSearch.getAuthor());
                    System.out.println("ISBN: "+itemToSearch.getIsbn());
                    System.out.println("Kütüphane ID: "+itemToSearch.getId());
                    System.out.println("Raf durumu: " + itemToSearch.getIsAvailable());
                    if (!itemToSearch.getIsAvailable()){
                        System.out.println("Ödünç alan: " + itemToSearch.getBorrowedBy());
                    }                }
                else {
                    System.out.println("Ürün bulunamadı!");
                }
            }
            else if (selectedSearch == 4){
                break searchLoop;
            }

        }
            //Hesap ekleme işlemleri
        } else if (selected == 5){
            Random random = new Random();
            accountLoop:
            while(true){
            System.out.println("Üye eklemek için 1'e, Kütüphaneci eklemek için 2'ye, çıkmak için 3'e basın.");
            int selectedAccount = scanner.nextInt();
            scanner.nextLine();
            if (selectedAccount == 1){
                System.out.println("Üye hesabı ekliyorsunuz.");
                System.out.println("Kullanıcı adı: ");
                String name = scanner.nextLine();
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString().substring(0, 8);  // First 8 characters
                System.out.println("Şifre: ");
                String password = scanner.next();
                //şifremizi şifreliyoruz
                String hashedPass = PasswordHasher.hashPassword(password);
                Member member = new Member(name,id,hashedPass);
                librarian.addMember(member);
            }
                else if (selectedAccount == 2){
                System.out.println("Kütüphaneci hesabı ekliyorsunuz.");
                System.out.println("Kullanıcı adı: ");
                String name = scanner.nextLine();
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString().substring(9, 15);  // First 8 characters
                System.out.println("Şifre: ");
                String password = scanner.next();
                //şifremizi şifreliyoruz
                String hashedPass = PasswordHasher.hashPassword(password);
                Librarian addedLibrarian = new Librarian(name,id,hashedPass);
                librarian.addLibrarian(addedLibrarian);
                }
                else if (selectedAccount == 3){
                    break accountLoop;
            }
            }
        } else if (selected == 6){

                boolean flag = false;
                scanner.nextLine();
                System.out.println("Kullanıcı ID girin: ");
                String id = scanner.nextLine();
                System.out.println("Kullanıcı Şifresini girin: ");
                String password = scanner.nextLine();
                String hashedPass = PasswordHasher.hashPassword(password);
            try {
                accDeleteLoop:
                for (Member i : Member.members) {
                    if (i.getMemberID().equals(id) && i.getPassword().equals(hashedPass)){
                    if (i instanceof Librarian) {
                        librarian.removeLibrarian((Librarian) i);
                        flag = true;
                        System.out.println("Kütüphaneci hesabı başarıyla silinmiştir.");
                        break accDeleteLoop;
                        //todo hesap silindiği zaman tekrar ana menüye gitsin
                    } else {
                        librarian.removeMember(i);
                        flag = true;
                        System.out.println("Üye hesabı başarıyla silinmiştir.");
                    }
                }
                    else if(!i.getMemberID().equals(id)){
                        flag = false;
                    }
                    else{
                        flag = false;
                    }
                }
                if (!flag){
                    System.out.println("Hesap silinemedi. (öyle bir hesap yok ya da şifreyi yanlış girdiniz.");
                }
            } catch (Exception e) {
                if(!flag){
                System.out.println("Hata! " + e.getMessage());
            }
            }
        }
        else if (selected == 8){
            librarian.showAllMembers();
        }
        else if (selected == 7){
            scanner.nextLine();
            System.out.println("Hesap adını girin: ");
            String hesapAdi = scanner.nextLine();
            boolean flag = false;
            String id = "katze und hund";
            for (Member i : Member.members) {
                if (i.getName().trim().equals(hesapAdi.trim())){
                    flag = true;
                    id = i.getMemberID();
                }
                else{
                    flag = false;
                }
            }
            if (flag){
                System.out.println("Kullanıcı bulundu! ID: " + id);
            }
            else{
                System.out.println("Kullanıcı bulunamadı.");
            }
        }
        else if (selected == 9){
            try {
                scanner.nextLine();
                //kullanıcı şifresi değiştirme menüsü
                boolean flag = false; //eğer şifre ve id bir kullanıcı ile uyuşuyorsa true olacak.
                //id ve eski şifre değerlerini alıyoruz önce
                System.out.println("Kullanıcı ID'si girin: ");
                String id = scanner.nextLine();
                System.out.println("Eski şifreyi girin: ");
                String password = scanner.nextLine();
                //eskisini de hashiyle karşılaştıracağımızdan dolayı hashliyoruz
                String hashedPass = PasswordHasher.hashPassword(password);
                System.out.println("Yeni şifreyi girin: ");
                String newPassword = scanner.nextLine();
                //yeni şifreyi hashliyoruz
                String hashedNewPass = PasswordHasher.hashPassword(newPassword);
                for (Member i : Member.members) {
                    if (i.getMemberID().equals(id) && i.getPassword().equals(hashedPass)) {
                        flag = true;
                        i.setPassword(hashedNewPass);
                    } else {
                        flag = false;
                    }
                }
                if (flag) {
                    System.out.println("Şifre başarıyla değiştirilmiştir.");
                } else {
                    System.out.println("Hata!");
                }
            }catch (Exception e) {
                System.out.println("Hata! " + e.getMessage());
            }
        }
        else if (selected == 10){
            break libMenuLoop;
        }
    }

    }
    public static void memberMenu(Member member){
        Scanner scannerMember = new Scanner(System.in);
        Searchable searchMethod = new Library();
        System.out.println("Hoş geldin, " + member.getName());
        memberMenuLoop:
        while (true){
        System.out.println("Yapabileceğiniz işlemler: \n 1. Kitap veya dergi arayın \n 2. Bir ürün ödünç alın \n 3. Bir ürünü geri getirin \n 4. Ödünç alınmış ürünleri görüntüleyin \n 5. Çıkış yap");
        int selected = scannerMember.nextInt();
        if (selected == 2) {
            try {
                System.out.println("Ödünç almak istediğiniz ürünün ID'sini girin:");
                int borrowId = scannerMember.nextInt();
                if (searchMethod.searchByID(borrowId) == null) {
                    System.out.println("Girdiğiniz ID'ye sahip ürün bulunamadı!");
                } else {
                    LibraryItem book = searchMethod.searchByID(borrowId);
                    member.borrowItem(book);
                }
            } catch (Exception e) {
                System.out.println("Hata! " + e.getMessage());
            }
        }
        else if (selected == 1){
            try{
            System.out.println("Kitabın ID'sini girin");
            int searchID = scannerMember.nextInt();
            LibraryItem foundItem = searchMethod.searchByID(searchID);
            if (foundItem != null){
                System.out.println("Ürün adı: " + foundItem.getTitle() + "\n");
            }
            else{
                System.out.println("Ürün bulunamadı!");
            }} catch (Exception e) {
                if(e instanceof InputMismatchException){
                    System.out.println("ID kısmına sadece sayı girebilirsiniz.");
                }
            }
        }
        else if (selected == 5){
            break memberMenuLoop;
        }
        else if (selected == 3){
            try{
            System.out.println("Geri getirmek istediğiniz ürünün ID'sini girin: ");
            int returnID = scannerMember.nextInt();
            LibraryItem book = searchMethod.searchByID(returnID);
            if (searchMethod.searchByID(returnID) == null){
                System.out.println("Girdiğiniz ID'ye sahip ürün bulunamadı!");
            }
            else if (book.getIsAvailable()){
                System.out.println("Kitap ödünç alınmamış.");
            }
            // biz mi almışız bu kitabı yoksa başkası mı?
            else if (Objects.equals(book.getBorrowedBy(), member.getName())){
                System.out.println("Kitap başkası tarafından ödünç alınmış!");
                return;
            }
            else {
                member.returnItem(book);
            }
        }catch (Exception e){
                System.out.println("Hata! "+ e.getMessage());
            }
        }
        else if (selected == 4){
            member.viewBorrowedItems();
        }
        }
    }
}
import java.util.ArrayList;


public class MemberManager {

    private void createDefaultLibrarian(){
        // varsayılan kullanıcı ekleme
        String hashedPassword = PasswordHasher.hashPassword("Kedi");
        Librarian defaultLib = new Librarian("Default","A1",hashedPassword);
        Librarian.members.add(defaultLib);
    }
    public MemberManager(){
        createDefaultLibrarian();
    }
    // giriş yapma sistemi.
    public Member Login(String memberID, String memberPassword){
        for(Member member : Librarian.members){
            if(member.getMemberID().equals(memberID) && member.getPassword().trim().equals(memberPassword.trim())){
                        return member;
                    }

                }

        return null;

    }
}

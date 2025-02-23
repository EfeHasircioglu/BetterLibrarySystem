public interface Searchable {
    LibraryItem searchByTitle(String title);
    LibraryItem searchByISBN(String isbn);
    LibraryItem searchByID(int id);
}

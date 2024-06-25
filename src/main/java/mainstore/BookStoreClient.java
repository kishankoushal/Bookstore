package mainstore;
import bookstore.BookServiceGrpc;
import bookstore.BookServiceOuterClass.Book;
import bookstore.BookServiceOuterClass.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BookStoreClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

        // Add a book
        Book book = Book.newBuilder().setIsbn("123").setTitle("Test Book").addAuthors("Author 1").setPageCount(100).build();
        AddBookResponse addResponse = stub.addBook(AddBookRequest.newBuilder().setBook(book).build());
        System.out.println("Add book success: " + addResponse.getSuccess());

        // Get all books
        GetBooksResponse getResponse = stub.getBooks(GetBooksRequest.newBuilder().build());
        System.out.println("Books: " + getResponse.getBooksList());

        // Update a book
        Book updatedBook = Book.newBuilder().setIsbn("123").setTitle("Updated Test Book").addAuthors("Author 1").setPageCount(150).build();
        UpdateBookResponse updateResponse = stub.updateBook(UpdateBookRequest.newBuilder().setBook(updatedBook).build());
        System.out.println("Update book success: " + updateResponse.getSuccess());

        // Get all books again
        getResponse = stub.getBooks(GetBooksRequest.newBuilder().build());
        System.out.println("Books after update: " + getResponse.getBooksList());

        // Delete a book
        DeleteBookResponse deleteResponse = stub.deleteBook(DeleteBookRequest.newBuilder().setIsbn("123").build());
        System.out.println("Delete book success: " + deleteResponse.getSuccess());

        // Get all books after deletion
        getResponse = stub.getBooks(GetBooksRequest.newBuilder().build());
        System.out.println("Books after deletion: " + getResponse.getBooksList());

        channel.shutdown();
    }
}

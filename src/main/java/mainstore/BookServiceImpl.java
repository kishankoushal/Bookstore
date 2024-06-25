package mainstore;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import bookstore.BookServiceGrpc;
import bookstore.BookServiceOuterClass.*;
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    private final ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();

    public void addBook(AddBookRequest request, StreamObserver<AddBookResponse> responseObserver) {
        Book book = request.getBook();
        books.put(book.getIsbn(), book);
        AddBookResponse response = AddBookResponse.newBuilder().setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void updateBook(UpdateBookRequest request, StreamObserver<UpdateBookResponse> responseObserver) {
        Book book = request.getBook();
        books.put(book.getIsbn(), book);
        UpdateBookResponse response = UpdateBookResponse.newBuilder().setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void deleteBook(DeleteBookRequest request, StreamObserver<DeleteBookResponse> responseObserver) {
        books.remove(request.getIsbn());
        DeleteBookResponse response = DeleteBookResponse.newBuilder().setSuccess(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void getBooks(GetBooksRequest request, StreamObserver<GetBooksResponse> responseObserver) {
        List<Book> bookList = new ArrayList<>(books.values());
        GetBooksResponse response = GetBooksResponse.newBuilder().addAllBooks(bookList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

package teststore;
import bookstore.BookServiceOuterClass.Book;
import bookstore.BookServiceGrpc;
import bookstore.BookServiceOuterClass.*;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import mainstore.BookServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookServiceImplTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void addBook() throws Exception {
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new BookServiceImpl()).build().start());
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        Book book = Book.newBuilder().setIsbn("123").setTitle("Test Book").addAuthors("Author 1").setPageCount(100).build();
        AddBookResponse response = blockingStub.addBook(AddBookRequest.newBuilder().setBook(book).build());

        assertEquals(true, response.getSuccess());
    }

    @Test
    public void getBooks() throws Exception {
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new BookServiceImpl()).build().start());
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        Book book1 = Book.newBuilder().setIsbn("123").setTitle("Test Book 1").addAuthors("Author 1").setPageCount(100).build();
        Book book2 = Book.newBuilder().setIsbn("456").setTitle("Test Book 2").addAuthors("Author 2").setPageCount(200).build();

        blockingStub.addBook(AddBookRequest.newBuilder().setBook(book1).build());
        blockingStub.addBook(AddBookRequest.newBuilder().setBook(book2).build());

        GetBooksResponse response = blockingStub.getBooks(GetBooksRequest.newBuilder().build());

        assertEquals(2, response.getBooksCount());
    }
}

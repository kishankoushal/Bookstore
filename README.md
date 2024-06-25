# gRPC Java Project with Protobuf and Gradle

This project demonstrates a simple gRPC-based Java application using Protocol Buffers (Protobuf) for defining services and messages, and Gradle as the build tool.

## Project Structure

The project structure is as follows:

- `build.gradle`: Configuration file for Gradle build system.
- `settings.gradle`: Settings file for Gradle.
- `src/main/java`: Directory for main Java source files.
- `src/main/proto`: Directory for Protobuf (.proto) definition files.
- `src/test/java`: Directory for unit tests.

### Versions

- JDK 21 
- Gradle 8.5

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/kishankoushal/Bookstore.git
   cd Bookstore
2. **Build the Project:**
   ```bash
   ./gradlew build
   ./gradlew generateProto
  ## Running the Service

### Start the Server

1. Open `BookStoreServer.java(src/main/java/mainstore/BookStoreServer)` in your IDE.

2. Run the `main` method to start the gRPC server.

### Run the Client

1. Open `BookStoreClient.java(src/main/java/mainstore/BookStoreClient)` in your IDE.

2. Run the `main` method to start the gRPC client.


## Implemented RPCs and Their Expected Behavior

### AddBook
Adds a new book to the store. If a book with the same ISBN already exists, the operation fails (`success = false`). Otherwise, the book is added successfully (`success = true`).

### UpdateBook
Updates an existing book in the store based on its ISBN. Returns `success = true` if the book is updated successfully. Returns `success = false` if the book with the specified ISBN does not exist.

### DeleteBook
Deletes a book from the store based on its ISBN. Returns `success = true` if the book is deleted successfully. Returns `success = false` if the book with the specified ISBN does not exist.

### GetBooks
Retrieves all books currently stored in the book store. Returns a list of all books.



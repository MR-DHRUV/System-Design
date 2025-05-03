class Book {

    public Book() {
    }
};

class Invoice {

    private Book book;

    public Invoice() {
    }

    public double calculateTotal() {
        // Calculates the total price of the invoice
        return 0.0;
    }
};

class InvoicePrinter {

    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        // Prints the invoice with all the details
    }
}

// class InvoicePersistence  {

//     Invoice invoice;

//     public InvoicePersistence (Invoice invoice) {
//         this.invoice = invoice;
//     }

//     public void saveToFile(String filename) {
//         // Creates a file with given name and writes the invoice
//     }

//     // Suppose we want to save the invoice to a database as well.
//     // public void saveToDatabase(String dbName) {
//     // }
//     // Adding a new m violates the open-closed principle, as this class is not open for extension and closed for modification.
// }


// Better approach
interface InvoicePersistence {
    public void save(Invoice invoice);
}

class DatabasePersistence implements InvoicePersistence {
    @Override
    public void save(Invoice invoice) {
        // Save to DB
    }
}

class FilePersistence implements InvoicePersistence {
    @Override
    public void save(Invoice invoice) {
        // Save to file
    }
}

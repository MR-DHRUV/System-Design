class Book {
    public Book() {}
};

class Invoice {
    private Book book;

    public Invoice() {
    }

    public double calculateTotal() {
        // Calculates the total price of the invoice
        return 0.0;
    }

    // Below is violation of single responsibility principle, as this class is doing too many things and has mutiple reasons to change.
    
    // public void printInvoice() {
    //     // Prints the invoice with all the details
    // }

    // public void saveToFile(String filename) {
    //     // Creates a file with given name and writes the invoice
    // }
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


class InvoiceSaver {

    Invoice invoice;

    public InvoiceSaver(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToFile(String filename) {
        // Creates a file with given name and writes the invoice
    }
}

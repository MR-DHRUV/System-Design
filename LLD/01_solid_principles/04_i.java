

// This violates the interface segregation principle, as the interface is too large and has multiple methods that are not relevant to all classes that implement it.
// A simple printer class may not need to implement the scan and fax methods, but this interface forces it to do so violating other principles like liskov substitution, single responsibility, etc.
interface MultiFunctionDevice {
    void print();
    void scan();
    void fax();
}



interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

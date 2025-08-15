// abtract class
abstract class DataMiner {

    protected String documentPath;

    protected abstract void openDocument();

    protected abstract void extractData();

    // Methods that will be same for all document types
    protected void cleanData() {
        System.out.println("Cleaning data from document.");
    }

    protected void analyzeData() {
        System.out.println("Analyzing data from document.");
    }

    protected abstract void closeDocument();

    public final void processDocument(String path) {
        this.documentPath = path;
        openDocument();
        extractData();
        cleanData();
        analyzeData();
        closeDocument();
    }
}

// Concrete class
class CSVDataMiner extends DataMiner {
    @Override
    protected void openDocument() {
        System.out.println("Opening CSV document at " + documentPath);
    }

    @Override
    protected void extractData() {
        System.out.println("Extracting data from CSV document.");
    }

    @Override
    protected void closeDocument() {
        System.out.println("Closing CSV document.");
    }
}

class JSONDataMiner extends DataMiner {
    @Override
    protected void openDocument() {
        System.out.println("Opening JSON document at " + documentPath);
    }

    @Override
    protected void extractData() {
        System.out.println("Extracting data from JSON document.");
    }

    @Override
    protected void closeDocument() {
        System.out.println("Closing JSON document.");
    }
}

public class Main {
    public static void main(String[] args) {
        DataMiner csvMiner = new CSVDataMiner();
        csvMiner.processDocument("path/to/csv");

        DataMiner jsonMiner = new JSONDataMiner();
        jsonMiner.processDocument("path/to/json");
    }
}

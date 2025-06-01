// Problem Statement: Design a file system where files and directories can be treated uniformly.
// A directory can contain files and other directories, and you can perform operations like listing contents, adding files, and calculating total size.

import java.util.*;

// Componet class
abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void ls();

    public abstract int getSize();
}

// Leaf class
class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void ls() {
        System.out.println(name + " (" + size + " bytes)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// Composite class
class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void ls() {
        System.out.println(name + " (directory):");
        for (FileSystemComponent component : children) {
            component.ls();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}


public class Main {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);
        File file3 = new File("file3.txt", 300);

        // Create directories
        Directory dir1 = new Directory("dir1");
        Directory dir2 = new Directory("dir2");

        // Add files to directories
        dir1.add(file1);
        dir1.add(file2);
        dir2.add(file3);

        // Create a root directory and add subdirectories
        Directory root = new Directory("root");
        root.add(dir1);
        root.add(dir2);

        // List contents of the root directory
        root.ls();

        // Calculate total size of the root directory
        System.out.println("Total size: " + root.getSize() + " bytes");
    }
}

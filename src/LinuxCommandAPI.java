/*
* Implement linux find command as an api ,the api will support finding files that has given size requirements and a file with a certain format like

find all file >5mb
find all xml
Assume file class
{
get name()
directorylistfile()
getFile()
create a library flexible that is flexible
Design clases,interfaces.
* */

import java.util.ArrayList;
import java.util.List;

interface Filter {
    boolean apply(File file);
}

class SizeFilter implements Filter {
    private long size;

    public SizeFilter(long size) {
        this.size = size;
    }

    @Override
    public boolean apply(File file) {
        return file.getSize() > this.size;
    }
}

class ExtensionFilter implements Filter {
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean apply(File file) {
        return file.getExtension().equalsIgnoreCase(this.extension);
    }
}

class File {
    private String name;
    private boolean isDirectory;
    private long size;
    private String extension;
    private List<File> children = new ArrayList<File>();

    public File(String name, long size) {
        this.name = name;
        this.size = size;
        this.isDirectory = !name.contains(".");
        this.extension = isDirectory ? "" : name.substring(name.lastIndexOf(".") + 1);
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    public List<File> getChildren() {
        return children;
    }

    public void addChild(File child) {
        if (this.isDirectory) {
            this.children.add(child);
        }
    }

    @Override
    public String toString() {
        return "{" + this.name + "}";
    }
}

class FileSystem {
    private List<Filter> filters = new ArrayList<Filter>();

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }

    public List<File> traverse(File root) {
        List<File> result = new ArrayList<File>();
        traverseUtil(root, result);
        return result;
    }

    private void traverseUtil(File root, List<File> result) {
        if (root.isDirectory()) {
            for (File child : root.getChildren()) {
                traverseUtil(child, result);
            }
        } else {
            for (Filter filter : filters) {
                if (filter.apply(root)) {
                    result.add(root);
                    break; // Ensures a file is only added once if it matches any filter
                }
            }
        }
    }
}

// Example usage
public class LinuxCommandAPI {
    public static void main(String[] args) {
        File f1 = new File("StarTrek.txt", 5);
        File f2 = new File("StarWars.xml", 10);
        File f3 = new File("JusticeLeague.txt", 15);
        File f4 = new File("IronMan.txt", 9);
        File f5 = new File("Spock.jpg", 1);
        File f6 = new File("BigBangTheory.txt", 50);
        File f7 = new File("MissionImpossible", 10);
        File f8 = new File("BreakingBad", 11);
        File f9 = new File("root", 100);

        f9.addChild(f7);
        f9.addChild(f8);
        f7.addChild(f1);
        f7.addChild(f2);
        f7.addChild(f3);
        f8.addChild(f4);
        f8.addChild(f5);
        f8.addChild(f6);

        FileSystem fs = new FileSystem();
        fs.addFilter(new SizeFilter(5));
        fs.addFilter(new ExtensionFilter("txt"));

        List<File> results = fs.traverse(f9);
        for (File file : results) {
            System.out.println(file);
        }
    }
}

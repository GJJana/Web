package Exer;

public class FileSystem {
    private Folder rootDirectory;

    public FileSystem() {
        this.rootDirectory=new Folder("root");
    }
    void addFile (IFile file) throws FileNameExistsException {
        this.rootDirectory.addFile(file);
    }
    long findLargestFile (){
        return this.rootDirectory.findLargestFile().getFileSize();
    }
    void sortBySize(){
        this.rootDirectory.sortBySize();
    }

    @Override
    public String toString() {
        return rootDirectory.getFileInfo(rootDirectory);
    }
}

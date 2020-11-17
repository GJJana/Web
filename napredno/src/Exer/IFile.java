package Exer;

public interface IFile {

    String getFileName();
    long getFileSize();
    String getFileInfo(IFile f);
    void sortBySize();
    IFile findLargestFile();

}

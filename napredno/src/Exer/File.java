package Exer;

public class File implements IFile ,Comparable<IFile> {

    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String getFileName() {
        return name;

    }


    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(IFile f) {
        return String.format("File name:%10s File size:%10d\n",name,size);
    }

    @Override
    public void sortBySize() {

    }


    @Override
    public IFile findLargestFile() {
        return this;
    }


    @Override
    public int compareTo(IFile o) {
        return (int)(size-o.getFileSize());
    }
    @Override
    public String toString() {
        return getFileInfo(this);
    }
}

package Exer;

import java.util.*;
import java.util.stream.Collectors;

public class Folder implements IFile ,Comparable<IFile>{

    private String name;
    private long size;
    private List<IFile> files;

    public Folder(String name, long size) {
        this.name = name;
        this.size = size;
        this.files=new ArrayList<>();
    }
    public Folder(String name){
        this.name=name;
        this.size = 0;
        this.files=new ArrayList<>();

    }
    public void addFile(IFile file) throws FileNameExistsException{
        if(files.contains(file))
            throw new FileNameExistsException(file.getFileName());
        files.add(file);
        this.size+=file.getFileSize();
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
       StringBuilder sb=new StringBuilder();
       sb.append(String.format("Folder name:%10s Folder size:%10d\n",name,size));
        files.forEach(k->sb.append(String.format("\t%s",getFileInfo(k))));
        return sb.toString();
    }

    @Override
    public void sortBySize() {
        files=files.stream().sorted().collect(Collectors.toList());
        files.forEach(k->{
            if(k instanceof Folder)
                k.sortBySize();
        });
    }

    @Override
    public IFile findLargestFile() {
        sortBySize();

        Map<IFile,Long> tmp=new HashMap<>();
        files.forEach(c->{
            if(c instanceof Folder)
                tmp.put(c.findLargestFile(),c.findLargestFile().getFileSize());
            else tmp.put(c,getFileSize());
        });

        return tmp.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).findFirst().orElse(null).getKey();
       //return  tmp.values().stream().max(Comparator.comparingLong(Long::longValue)).orElse(0.0);

    }

    @Override
    public int compareTo(IFile o) {
        return (int)(size-o.getFileSize());
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public List<IFile> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return getFileInfo(this);
    }
}

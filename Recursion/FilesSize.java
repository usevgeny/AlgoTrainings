package Recursion;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesSize {
    public static void main(String[] args) {
        double allFilesSize = getAllFilesSize("/home/evgeny/Video");
        System.out.println("Totel files size: "+allFilesSize/(1024*1024*1024)+"GB");
    }
    
    public static double getAllFilesSize(String filePath) {
        Set<File> allFiles = Stream.of(new File(filePath).listFiles())
        .collect(Collectors.toSet());
        double size=0;
        for(File file: allFiles) {
            if(!file.isDirectory()) {
            size += file.length();
                System.out.println(file.getAbsolutePath()+" size: "+size);
            }else{
                System.out.println(file.getAbsolutePath());
                size+=getAllFilesSize(file.getAbsolutePath());
            }
        }
        return  size;
    }

}

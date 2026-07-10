import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoveBOM {
    public static void main(String[] args) throws IOException {
        File dir = new File("d:\\oop_java_cuoiky\\QuanLyGara\\src\\main\\java");
        removeBomInDir(dir);
    }
    
    public static void removeBomInDir(File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                removeBomInDir(file);
            } else if (file.getName().endsWith(".java")) {
                byte[] bytes = Files.readAllBytes(file.toPath());
                if (bytes.length >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                    byte[] withoutBom = new byte[bytes.length - 3];
                    System.arraycopy(bytes, 3, withoutBom, 0, withoutBom.length);
                    Files.write(file.toPath(), withoutBom);
                    System.out.println("Removed BOM from " + file.getName());
                }
            }
        }
    }
}

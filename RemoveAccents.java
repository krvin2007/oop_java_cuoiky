import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class RemoveAccents {
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(nfdNormalizedString).replaceAll("");
        result = result.replaceAll("Đ", "D");
        result = result.replaceAll("đ", "d");
        return result;
    }

    public static void main(String[] args) throws Exception {
        String path = "d:\\oop_java_cuoiky\\Data\\quangara.sql";
        String content = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
        content = deAccent(content);
        Files.write(Paths.get(path), content.getBytes("UTF-8"));
        System.out.println("Done");
    }
}

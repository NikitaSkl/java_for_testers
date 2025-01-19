import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        var configFile=new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsoluteFile());
        System.out.println(configFile.exists());
    }
}

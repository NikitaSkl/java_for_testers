import java.io.File;
import java.util.Arrays;

public class Hello {
    public static void main(String[] args) {
            var x=1;
            var y=0;
            if (y==0) System.out.println("Division by zero is not allowed");
            else {
                var z=divide(x, y);
                System.out.println("Hello, world!");
            }
            var configFile=new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsoluteFile());
        System.out.println(configFile.exists());
        System.out.println(new File("").getAbsolutePath());
        String[] array={"a","b","c"};
    }

    private static int divide(int x, int y) {
        var z= x / y;
        return z;
    }
    private static int a(int a,String b){
        return 0;
    }
    private static int a(String b,int a){
        return 0;
    }
}

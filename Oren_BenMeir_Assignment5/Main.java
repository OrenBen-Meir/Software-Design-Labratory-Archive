import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
    static String checkProtected(String value){
        if (value.length()<9){
            int moreSpaces = 9 - value.length();
            StringBuilder zeros = new StringBuilder();
            IntStream.range(0,moreSpaces).forEach(x->zeros.append('0'));
            value = zeros.toString() + value;
        }
        else if (value.length()>9) throw new IllegalArgumentException("value too large");
        if (Pattern.matches("\\d\\d\\d\\d\\d\\d[.]\\d\\d",value)){
            StringBuilder markedValue = new StringBuilder(value);
            for (int i = 0; i<value.length();i++){
                if (markedValue.charAt(i) != '0') break;
                markedValue.setCharAt(i,'*');
            }
            return markedValue.toString();
        }
        else{
            throw new IllegalArgumentException("pattern mismatch");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do{
            try {
                System.out.println("Input a dollar amount at most 8 digit value with 2 decimal places:");
                String val = input.nextLine();
                System.out.println("check protected format: $"+checkProtected(val));
                break;
            }
            catch (IllegalArgumentException e){
                System.err.println(e);
                System.out.println("Try again!!\n---------------");
            }
        }while (true);
    }
}

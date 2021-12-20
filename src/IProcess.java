import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IProcess {
    static String process(String str) {
        Pattern patternForExp = Pattern.compile("[()0-9]*( ){0,}([+-/*]( ){0,}[()0-9]{0,})*");
        Matcher matcherForExp = patternForExp.matcher(str);
        while (matcherForExp.find()) {
            String expression = matcherForExp.group();

            if(expression.equals("") || expression.equals(" ")) {
                continue;
            }
            try {
                str = str.replace(expression, Ideone.calc(ExpressionParser.parse(expression)));
            }
            catch(Exception e){

            }
        }

        return str;
    }
}

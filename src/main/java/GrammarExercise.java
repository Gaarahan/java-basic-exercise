import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gaarahan
 */
public class GrammarExercise {
  public static void main(String[] args) {
    //需要从命令行读入
    Scanner in = new Scanner(System.in);
    String firstWordList = in.nextLine();
    String secondWordList = in.nextLine();


    List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
    //按要求输出到命令行
    System.out.println(result);

  }
  public static List<String> findCommonWordsWithSpace(String firstWordStr, String secondWordStr) {
    final String wordPattern = "^([a-zA-Z]+,)*[a-zA-Z]+$";
    if (!Pattern.matches(wordPattern, firstWordStr) || !Pattern.matches(wordPattern, secondWordStr)) {
      throw new RuntimeException("input not valid");
    }

    String[] firstWordArray = firstWordStr.split(",");
    String[] secondWordArray = secondWordStr.split(",");

    List<String> secondWordList = Stream.of(secondWordArray)
        .map(String::toUpperCase)
        .collect(Collectors.toList());
    return Stream.of(firstWordArray)
        .map(String::toUpperCase)
        .filter(secondWordList::contains)
        .distinct()
        .sorted()
        .map(itm -> itm.replace("", " ").trim())
        .collect(Collectors.toList());
  }
}

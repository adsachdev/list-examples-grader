import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// interface StringChecker { boolean checkString(String s); }


class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class startsWithA implements StringChecker {
  public boolean checkString(String s) {
    return s.startsWith("a");
  }
}

class isQuestion implements StringChecker {
  public boolean checkString(String s) {
    return s.endsWith("?");
  }
}

public class TestListExamples {

  StringChecker sc1 = new IsMoon();
  StringChecker sc2 = new startsWithA();
  StringChecker sc3 = new isQuestion();


  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test
  public void testMergeEmpty() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> empty = new ArrayList<>();
    assertEquals(ListExamples.merge(left, empty), left);
  }

  @Test
  public void testMergeEmpty2() {
    List<String> leftEmpty = new ArrayList<>();
    List<String> rightEmpty = new ArrayList<>();
    assertEquals(ListExamples.merge(leftEmpty, rightEmpty), leftEmpty);
  }

  @Test
  public void testFilter() {
    List<String> someQs = Arrays.asList("a?", "cd", "hm?", "hi", "bye?");
    List<String> output = Arrays.asList("a?", "hm?", "bye?");
    assertEquals(ListExamples.filter(someQs, sc3), output); 
  }
}

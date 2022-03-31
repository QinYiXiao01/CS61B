import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome("maooam"));
        assertTrue(palindrome.isPalindrome("qqq"));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("goushi"));
        assertFalse(palindrome.isPalindrome("heihei"));
    }

    @Test
    public void testOffByOnePalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("acdb", cc));
        assertFalse(palindrome.isPalindrome("goushi", cc));
    }
}

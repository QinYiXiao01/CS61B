import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void offByOne() {
        assertFalse(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('e', 'c'));
        assertTrue(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('e', 'e'));
    }

}

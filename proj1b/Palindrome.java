public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> Arraydeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Arraydeque.addLast(word.charAt(i));
        }
        return Arraydeque;
    }

    public boolean isPalindrome(String word) {
        String wordBackward = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            wordBackward += word.charAt(i);
        }
        return wordBackward.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        for (int i = 0; i <= len / 2 - 1; i++) {
            boolean flag = cc.equalChars(word.charAt(i), word.charAt(len - 1 - i));
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}

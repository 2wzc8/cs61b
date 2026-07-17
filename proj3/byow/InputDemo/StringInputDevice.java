<<<<<<< HEAD
package byow.InputDemo;

/**
 * Created by hug.
 */
public class StringInputDevice implements InputSource  {
    private String input;
    private int index;

    public StringInputDevice(String s) {
        index = 0;
        input = s;
    }

    public char getNextKey() {
        char returnChar = input.charAt(index);
        index += 1;
        return returnChar;
    }

    public boolean possibleNextInput() {
        return index < input.length();
    }
}
=======
package byow.InputDemo;

/**
 * Created by hug.
 */
public class StringInputDevice implements InputSource  {
    private String input;
    private int index;

    public StringInputDevice(String s) {
        index = 0;
        input = s;
    }

    public char getNextKey() {
        char returnChar = input.charAt(index);
        index += 1;
        return returnChar;
    }

    public boolean possibleNextInput() {
        return index < input.length();
    }
}
>>>>>>> b24ae4db6ca2bf5d99c46ac4369d07de99c81982

package learning.oop.interfaces;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}

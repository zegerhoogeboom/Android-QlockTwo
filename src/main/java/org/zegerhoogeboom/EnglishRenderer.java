package org.zegerhoogeboom;

import org.joda.time.LocalDateTime;

import java.util.*;

/**
 * @author Zeger Hoogeboom
 */
public class EnglishRenderer implements Renderer {

    private static String[] letters = {
            "I", "T", "L", "I", "S", "A", "S", "A", "M", "P", "M",
            "A", "C", "Q", "U", "A", "R", "T", "E", "R", "D", "C",
            "T", "W", "E", "N", "T", "Y", "F", "I", "V", "E", "X",
            "H", "A", "L", "F", "B", "T", "E", "N", "F", "T", "O",
            "P", "A", "S", "T", "E", "R", "U", "N", "I", "N", "E",
            "O", "N", "E", "S", "I", "X", "T", "H", "R", "E", "E",
            "F", "O", "U", "R", "F", "I", "V", "E", "T", "W", "O",
            "E", "I", "G", "H", "T", "E", "L", "E", "V", "E", "N",
            "S", "E", "V", "E", "N", "T", "W", "E", "L", "V", "E",
            "T", "E", "N", "S", "E", "O", "C", "L", "O", "C", "K"
    };

    private static Map<String, List<Integer>> positions = new HashMap<String, List<Integer>>() {{
        put("IT", Arrays.asList(0, 1));
        put("IS", Arrays.asList(3, 4));
        put("AM", Arrays.asList(7, 8));
        put("AM", Arrays.asList(9, 10));

        put("A", Arrays.asList(11));
        put("QUARTER", Arrays.asList(13, 14, 15, 16, 17, 18, 19));
        put("TWENTY", Arrays.asList(22, 23, 24, 25, 26, 27));
        put("TWENTYFIVE", Arrays.asList(22, 23, 24, 25, 26, 27, 28, 29, 30, 31));
        put("FIVE", Arrays.asList(28, 29, 30, 31));
        put("HALF", Arrays.asList(33, 34, 35, 36));
        put("TEN", Arrays.asList(38, 39, 40));
        put("TO", Arrays.asList(42, 43));
        put("PAST", Arrays.asList(44, 45, 46, 47));
        put("NINE", Arrays.asList(51, 52, 53, 54));
        put("ONE", Arrays.asList(55, 56, 57));
        put("SIX", Arrays.asList(58, 59, 60));
        put("THREE", Arrays.asList(61, 62, 63, 64, 65));
        put("FOUR", Arrays.asList(66, 67, 68, 69));
        put("FIVEH", Arrays.asList(70, 71, 72, 73));
        put("TWO", Arrays.asList(74, 75, 76));
        put("EIGHT", Arrays.asList(77, 78, 79, 80, 81));
        put("ELEVEN", Arrays.asList(82, 83, 84, 85, 86, 87));
        put("SEVEN", Arrays.asList(88, 89, 90, 91, 92));
        put("TWELVE", Arrays.asList(93, 94, 95, 96, 97, 98));
        put("TENH", Arrays.asList(99, 100, 101));
        put("OCLOCK", Arrays.asList(104, 105, 106, 107, 108, 109));

    }};
    private LocalDateTime now;

    public EnglishRenderer(LocalDateTime now) { //for (future) testing purposes
        this.now = now;
    }

    public EnglishRenderer() {
        this.now = LocalDateTime.now();
    }

    public List<String> getLetters() {
        return Arrays.asList(letters);
    }

    public String getLetter(int position) {
        return getLetters().get(position);
    }

    public boolean shouldShow(int position) {
        return positionsShouldShow().contains(position);
    }

    private List<Integer> positionsShouldShow() {
        return union(Arrays.asList(
                positions.get("IT"),
                positions.get("IS"),
                minuteIndexes(),
                hourIndexes()
        ));
    }

    private List<Integer> minuteIndexes() {
        int minute = LocalDateTime.now().getMinuteOfHour();
        if (minute == 0) return positions.get("OCLOCK");
        else if (minute >= 5 && minute < 10)
            return union(Arrays.asList(positions.get("FIVE"), positions.get("PAST")));
        else if (minute >= 10 && minute < 15)
            return union(Arrays.asList(positions.get("TEN"), positions.get("PAST")));
        else if (minute >= 15 && minute < 20)
            return union(Arrays.asList(positions.get("A"), positions.get("QUARTER"), positions.get("PAST")));
        else if (minute >= 20 && minute < 25)
            return union(Arrays.asList(positions.get("TWENTY"), positions.get("PAST")));
        else if (minute >= 25 && minute < 30)
            return union(Arrays.asList(positions.get("TWENTYFIVE"), positions.get("PAST")));
        else if (minute >= 30 && minute < 35)
            return union(Arrays.asList(positions.get("HALF"), positions.get("PAST")));
        else if (minute >= 35 && minute < 40)
            return union(Arrays.asList(positions.get("TWENTYFIVE"), positions.get("TO")));
        else if (minute >= 40 && minute < 45)
            return union(Arrays.asList(positions.get("TWENTY"), positions.get("TO")));
        else if (minute >= 45 && minute < 50)
            return union(Arrays.asList(positions.get("A"), positions.get("QUARTER"), positions.get("TO")));
        else if (minute >= 50 && minute < 55)
            return union(Arrays.asList(positions.get("TEN"), positions.get("TO")));
        else if (minute >= 55 && minute < 60)
            return union(Arrays.asList(positions.get("FIVE"), positions.get("TO")));
        return new ArrayList<Integer>();
    }


    private List<Integer> hourIndexes() {
        int hour = LocalDateTime.now().getHourOfDay();
        return union(Arrays.asList(hourIndexes(hour, now.getMinuteOfHour()), hourIndexes(hour - 12, now.getMinuteOfHour())));
    }

    private List<Integer> hourIndexes(int hour, int minute) {
        if (minute <= 30) {
            if (hour == 0) return positions.get("TWELVE");
            else if (hour == 1) return positions.get("ONE");
            else if (hour == 2) return positions.get("TWO");
            else if (hour == 3) return positions.get("THREE");
            else if (hour == 4) return positions.get("FOUR");
            else if (hour == 5) return positions.get("FIVEH");
            else if (hour == 6) return positions.get("SIX");
            else if (hour == 7) return positions.get("SEVEN");
            else if (hour == 8) return positions.get("EIGHT");
            else if (hour == 9) return positions.get("NINE");
            else if (hour == 10) return positions.get("TENH");
            else if (hour == 11) return positions.get("ELEVEN");
            else if (hour == 12) return positions.get("TWELVE");
        }

        if (hour == 0) return positions.get("ONE");
        else if (hour == 1) return positions.get("TWO");
        else if (hour == 2) return positions.get("THREE");
        else if (hour == 3) return positions.get("FOUR");
        else if (hour == 4) return positions.get("FIVEH");
        else if (hour == 5) return positions.get("SIX");
        else if (hour == 6) return positions.get("SEVEN");
        else if (hour == 7) return positions.get("EIGHT");
        else if (hour == 8) return positions.get("NINE");
        else if (hour == 9) return positions.get("TENH");
        else if (hour == 10) return positions.get("ELEVEN");
        else if (hour == 11) return positions.get("TWELVE");
        else if (hour == 12) return positions.get("ONE");

        return new ArrayList<Integer>();
    }


    private <T> List<T> union(List<List<T>> lists) {
        ArrayList<T> single = new ArrayList<T>();
        for (List<T> list : lists) {
            if (list == null) continue;
            single.addAll(list);
        }
        return single;
    }
}
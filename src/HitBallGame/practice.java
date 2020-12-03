package HitBallGame;

import java.util.ArrayList;
public class practice {
    public static void main (String[] args) {
        run();
    }
    public static void run () {
        ArrayList list = new ArrayList<String> ();
        for (int i = 0; i < 10; i++ ) {
            list.add(String.valueOf(i));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
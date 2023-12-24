package Recursion;

public class TowerOfHanoi {
    private static final String MOVEMENT_DESCRIPTION = "From %s, to %s";
public static void main(String[] args) {
    
    moveElements(3, "A", "B", "C");
}

public static void moveElements(int numberOfEls, String source, String auxiliary, String destination) {
    if(numberOfEls == 1) {
        System.out.println(String.format(MOVEMENT_DESCRIPTION, source, destination));
    } else {
        moveElements(numberOfEls-1,source,destination,auxiliary);
        System.out.println(String.format(MOVEMENT_DESCRIPTION, source, destination));
        moveElements(numberOfEls-1,auxiliary,source,destination);
    }
}
}

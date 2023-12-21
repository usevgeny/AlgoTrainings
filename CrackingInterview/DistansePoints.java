package CrackingInterview;

public class DistansePoints {
    
    public static void main(String[] args) {
        Point point1 = new Point(0,0);
        Point point2 = new Point(10,10);
        System.out.println(getDistance(point2, point1));
    }

    public static double getDistance(Point p1, Point p2) {
        double deltaX = p2.x - p1.x;
        double deltaY = p2.y - p1.y;
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }
    private static class Point {
        double x, y ;
        public Point(double x, double y) {
            this.x = x; 
            this.y = y;
        }
    }
}

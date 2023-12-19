package CrackingInterview;

public class SummOfFirstN {

    public static void main(String[] args) {
        int test = 12;
        int N = 1000000000;
        System.out.println(findSummFirsVersion(N));
        System.out.println(findSumFirstN(N));
    }
    
    public static int findSummFirsVersion(int N) {
        Long start = System.currentTimeMillis();
        
        int summ = 0;
        for(int i=0;i<=N;i++){
            if(i%2==0) {
                summ+=i;
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Time spent (millis) : "+(end - start));
        return summ;
    }
    
    
    // better solution -> to increment by 2 on every step
    public static int findSumFirstN(int N) {
        Long start = System.currentTimeMillis();
        int sum = 0;
        int number = 2;
        while(number <=N) {
            sum += number;
            number +=2;
        }
        Long end = System.currentTimeMillis();
        System.out.println("Time spent (millis) : "+(end - start));
        return sum;
    }

}

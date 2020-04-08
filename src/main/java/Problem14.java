import java.util.Random;

public class Problem14 {

    public static void main(String[] args) {
        System.out.println(calculatPi());
    }

    /**
     * This problem was asked by Google.

The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.

Hint: The basic equation of a circle is x2 + y2 = r2.
     * @return
     */

    public static double calculatPi() {
        int counter = 0;
        int total = 10000;
        Random rand = new Random();
        for(int i=0; i<total * total; i++) {
            double x = rand.nextInt(total+1) /(double)total;
            double y = rand.nextInt(total+1) /(double)total;
            ///for(double x=0; x<=1.0; x+=0.0001){
            //    for(double y=0; y<=1.0; y+=0.0001){
            //        total++;
                    if(x*x + y*y <= 1.0)
                        counter++;
            //    }
        }

        return counter * 4.0 / (double)(total*total);
    }
}

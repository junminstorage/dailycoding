import java.util.Random;

class Problem15 {

  /**
   * This problem was asked by Facebook.
    Given a stream of elements too large 
    to store in memory, pick a random element from the stream with uniform probability.

    generate random number [min, max]
    //1. new Random().nextInt(max - min + 1) + min
    //2. (int)(Math.random() * ((max - min) + 1)) + min

     Reservoir Sampling
   */
  int result = 0;
  int counter = 0;
  public void process(int number) {
      counter++;

    if(counter == 1)
      result = number;

    int rand = (int)(Math.random()*(counter)); // or new Random().nextInt(counter) ?, which one is better
    if(rand == counter-1) // or if(rand == 0) ?
      result = number;  
  }

  public int getResult() {
        return result;
  }

  public int[] reservoir(int[] numbers, int k) {
    int[] result = new int[k];
    for(int i=0; i<k; i++)
      result[i] = numbers[i];
    
    Random rand = new Random();
    for(int i=k; i<numbers.length; i++) {
      int pick = rand.nextInt(i+1);
      if(pick<k) {
        swap(numbers, i, pick);
      }
    }
    return result;
  }

  private void swap(int[] numbers, int i, int j) {
    int temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }
}
// Name: George Wael | N:11 | Class: 4TI

class Pump extends Thread {
    int from;
    int to;
    int amount;
    int times;
      Pump(int from,int to,int amount,int times){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.times = times;
      }      


    public void run() {
      for(int g=0 ; g<times ; g++){
        int first;
        int second;
        if(from<to){
          first = from;
          second = to;
        }
        else{
          first = to;
          second = from;
        }
        Main.locks[first].acquire();
        try{
          Thread.sleep(2);
        }
        catch(InterruptedException e){}
        Main.locks[second].acquire();

          Main.balance[from] -= amount;
          Main.balance[to] += amount;

          Main.locks[second].release();
          Main.locks[first].release();

    }
    }
}
public class Main {

    static int[] balance = {300,450,150};
    static MySemaphore[] locks = new MySemaphore[3];
    
    public static void main(String[] args) throws InterruptedException {

      for (int i=0 ; i<3 ; i++){
        locks[i] = new MySemaphore(1);
      }
      
      Pump P1 = new Pump(0,1,10,12);
      Pump P2 = new Pump(1,2,15,8);
      Pump P3 = new Pump(2,0,5,20);
      Pump P4 = new Pump(2,1,5,10);

      P1.start();
      P2.start();
      P3.start();
      P4.start();
        P1.join();
        P2.join();
        P3.join();
        P4.join();

        System.out.println("Balance TankA: " + balance[0]);
        System.out.println("Balance TankB: " + balance[1]);
        System.out.println("Balance TankC: " + balance[2]);
        System.out.println("Total: " + (balance[1] + balance[2] + balance[3]) ); 

    }
}
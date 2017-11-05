package assignment;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App implements Runnable
{
    //Example:
    //Round 1 :
    //N Trolls on a table - 10
    //N-1 Cookies on the table - 9
    //Hammer Hits - Random
    //When it hits — Trolls grab a cookie
    //=> One troll is out

    //Round 2 :

    //N-1 Trolls - 9
    //N-2 Cookies - 8
    //Hammer hits
    //=>Recursion until Trolls = 1
    static  int no_of_trolls;
    int  no_of_cookies = no_of_trolls -1;
    Map cookieMap, gameMap;
    int round = 1;


    void trollsAndCookies(int no_of_trolls) throws InterruptedException {
        //no_of_cookies = no_of_trolls-1;
        System.out.println(no_of_cookies);
        Random random_time = new Random();
        int time_in_millis = random_time.nextInt(100);
        if(no_of_trolls > 1 ){
            //Random time 1000 seconds
            Thread[] threads = new Thread[no_of_trolls];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(new App());
                threads[i].setName(i+"");
               // threads[i].sleep(time_in_millis);
                threads[i].start();
            }
            round++;
            no_of_cookies--;
            trollsAndCookies(no_of_trolls -1 );
        }

    }

    void grabCookie(int round, int troll, int cookie){
        cookieMap = new HashMap();
        cookieMap.put(troll, cookie);
        gameMap = new HashMap();
        gameMap.put(round, cookieMap);

   }

    public void run(){

        int curr_thread = Integer.parseInt(Thread.currentThread().getName());
        if(no_of_cookies > 0){
            grabCookie(round, curr_thread,no_of_cookies);
            no_of_cookies--;
            System.out.println("Troll "+ curr_thread + " Grabs the cookie ! and " + no_of_cookies + " are up for grabs !");

        }else{
            System.out.println("Troll "+ curr_thread + " is Out !");

        }

    }


    public static void main( String[] args ) throws InterruptedException {

        System.out.println("Enter size of Trolls : \n");
        Scanner trolls =  new Scanner(System.in);
        no_of_trolls = trolls.nextInt();
        App testGame = new App();
        testGame.trollsAndCookies(no_of_trolls);

    }
}

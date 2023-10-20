import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<ConnectionThread> list = new ArrayList<>();
        int cycles = 35;
        int jumper = 3;
        
        for (int jump : Utils.Range(1, cycles)) {
                try {

            System.out.println(String.format("Threads: %d", jump*jumper));

            for (int id : Utils.Range(1, jump * jumper)) {
                list.add(new ConnectionThread(id, false));
            }
            for (ConnectionThread element : list) {
                element.start();
            }
            try {
                Thread.sleep(500);                
            } catch (InterruptedException e) {
                e.getStackTrace();
            }

            list = new ArrayList<>();
            }
            catch (NullPointerException e) {
                System.out.println("Execution ended, no more connections available");
                // for (ConnectionThread thread : list) {
                //     thread.interrupt();
                // }
                break;
            }
        }
            
    }
}

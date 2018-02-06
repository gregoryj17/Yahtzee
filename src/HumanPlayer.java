import java.util.Arrays;
import java.util.Scanner;

public class HumanPlayer extends Player {

    Scanner scan = new Scanner(System.in);

    public HumanPlayer(){
        System.out.print("Name?: ");
        name = scan.nextLine();
    }

    @Override
    public boolean[] keepWhich(int[] dice) {
        boolean[] keep = new boolean[5];
        System.out.println("Dice: "+Arrays.toString(dice));
        System.out.print("Keep which dice? (Indexed 1-5): ");
        char[] toKeep = scan.nextLine().toCharArray();
        for(char c : toKeep){
            if(c>='1'&&c<='5'){
                keep[(c-'1')]=true;
            }
        }
        return keep;
    }

    @Override
    public int scoreWhere(int[] dice, int[] scores) {
        System.out.println("Dice: " + Arrays.toString(dice));
        for(int i=0;i<13;i++){
            System.out.print(i+": "+names[i]+": "+((scores[i]>-1)?(scores[i]+" points"):("empty")));
            if(i!=12)System.out.print(", ");
            if(i==5||i==12)System.out.println();
        }
        int loc = -1;
        while(loc<0||loc>12||scores[loc]!=-1){
            System.out.print("Score where?: ");
            loc = scan.nextInt();
            scan.nextLine();
            if(loc<0||loc>12||scores[loc]!=-1)System.out.println("Invalid location.");
        }
        return loc;
    }
}

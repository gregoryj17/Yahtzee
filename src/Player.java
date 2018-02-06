public class Player {

    String name;
    final String[] names = new String[]{"Ones","Twos","Threes","Fours","Fives","Sixes","Three of a Kind","Four of a Kind","Full House","Small Straight","Large Straight", "Yahtzee","Chance"};


    public Player(){
        name = "Computer Player";
    }

    public boolean[] keepWhich(int[] dice){
        return new boolean[]{false,false,false,false,false};
    }

    public int scoreWhere(int[] dice, int[] scores){
        for(int i=0;i<scores.length;i++){
            if(scores[i]==-1)return i;
        }
        return 0;
    }

}

import java.util.Arrays;

public class Game {

    Player player1, player2;
    int[] p1score, p2score;
    final int ONES=0,TWOS=1,THREES=2,FOURS=3,FIVES=4,SIXES=5,THREEOFAKIND=6,FOUROFAKIND=7,FULLHOUSE=8,SMALLSTRAIGHT=9,LARGESTRAIGHT=10,YAHTZEE=11,CHANCE=12;
    final String[] names = new String[]{"Ones","Twos","Threes","Fours","Fives","Sixes","Three of a Kind","Four of a Kind","Full House","Small Straight","Large Straight", "Yahtzee","Chance"};
    final int CATEGORIES = 13;


    public Game(){
        player1 = new HumanPlayer();
        player2 = new Player();
        p1score = new int[CATEGORIES];
        p2score = new int[CATEGORIES];
        for(int i=0;i<CATEGORIES;i++){
            p1score[i]=-1;
            p2score[i]=-1;
        }
        playGame();
    }

    public void playGame(){
        for(int i=0;i<CATEGORIES;i++){
            takeTurn(player1, p1score);
            takeTurn(player2, p2score);

            System.out.println("After turn "+i+" ("+names[i]+"), the scores are:");
            System.out.println(Arrays.toString(p1score));
            System.out.println(Arrays.toString(p2score));
        }
        finalScore();
    }

    public void takeTurn(Player p, int[] score){
        int[] dice = new int[5];
        boolean[] keep = new boolean[5];
        for(int i=0;i<3;i++) {
            dice = rollDice(dice, keep);
            if(i<2)keep = p.keepWhich(dice);
        }
        int category = p.scoreWhere(dice, score);
        scoreTurn(dice, score, category);
    }

    public int[] rollDice(int[] dice, boolean[] keep){
        for(int i=0;i<dice.length;i++){
            if(!keep[i]){
                dice[i]=(int)((Math.random()*60)/10)+1;
            }
        }
        return dice;
    }

    public void scoreTurn(int[] dice, int[] score, int category){
        int turnScore = 0;
        int[] occur = new int[7];
        for(int d : dice){
            occur[d]++;
        }
        switch (category){
            case ONES:{
                for(int d : dice){
                    if(d==1)turnScore++;
                }
                break;
            }
            case TWOS:{
                for(int d : dice){
                    if(d==2)turnScore+=2;
                }
                break;
            }
            case THREES:{
                for(int d : dice){
                    if(d==3)turnScore+=3;
                }
                break;
            }
            case FOURS:{
                for(int d : dice){
                    if(d==4)turnScore+=4;
                }
                break;
            }
            case FIVES:{
                for(int d : dice){
                    if(d==5)turnScore+=5;
                }
                break;
            }
            case SIXES:{
                for(int d : dice){
                    if(d==6)turnScore+=6;
                }
                break;
            }
            case THREEOFAKIND:{
                if(occur[1]>2||occur[2]>2||occur[3]>2||occur[4]>2||occur[5]>2||occur[6]>2){
                    for(int d : dice){
                        turnScore+=d;
                    }
                }
                break;
            }
            case FOUROFAKIND:{
                if(occur[1]>3||occur[2]>3||occur[3]>3||occur[4]>3||occur[5]>3||occur[6]>3){
                    for(int d : dice){
                        turnScore+=d;
                    }
                }
                break;
            }
            case FULLHOUSE:{
                if((occur[1]==3||occur[2]==3||occur[3]==3||occur[4]==3||occur[5]==3||occur[6]==3)&&(occur[1]==2||occur[2]==2||occur[3]==2||occur[4]==2||occur[5]==2||occur[6]==2)){
                    turnScore=25;
                }
                break;
            }
            case SMALLSTRAIGHT:{
                if(occur[3]>0&&occur[4]>0&&((occur[1]>0&&occur[2]>0)||(occur[2]>0&&occur[5]>0)||(occur[5]>0&&occur[6]>0))){
                    turnScore=30;
                }
                break;
            }
            case LARGESTRAIGHT:{
                if(occur[2]>0&&occur[3]>0&&occur[4]>0&&occur[5]>0&&(occur[6]>0||occur[1]>0)){
                    turnScore=40;
                }
                break;
            }
            case YAHTZEE:{
                if(occur[1]>4||occur[2]>4||occur[3]>4||occur[4]>4||occur[5]>4||occur[6]>4){
                    turnScore=50;
                }
                break;
            }
            case CHANCE:{
                for(int d : dice){
                    turnScore+=d;
                }
                break;
            }
        }
        score[category]=turnScore;
    }

    public boolean isOver(){
        for(int i=0;i<CATEGORIES;i++){
            if(p1score[i]==-1||p2score[i]==-1){
                return false;
            }
        }
        return true;
    }

    public void finalScore(){
        if(!isOver()){
            System.out.println("The game isn't finished!");
            return;
        }
        int p1f=0,p2f=0;
        for(int i=0;i<CATEGORIES;i++){
            p1f+=p1score[i];
            p2f+=p2score[i];
            if(i==SIXES){
                if(p1f>62){
                    System.out.println(player1.name+" scored the upper section bonus with "+p1f+" points!");
                    p1f+=35;
                }
                if(p2f>62){
                    System.out.println(player2.name+" scored the upper section bonus with "+p2f+" points!");
                    p2f+=35;
                }
            }
        }
        if(p1f>p2f){
            System.out.println(player1.name+" has won the game!\nFinal scores:\n"+player1.name+": "+p1f+"\n"+player2.name+": "+p2f);
        }else if(p2f>p1f){
            System.out.println(player2.name+" has won the game!\nFinal scores:\n"+player1.name+": "+p1f+"\n"+player2.name+": "+p2f);
        }else{
            System.out.println("It's a tie!\nFinal scores:\n"+player1.name+": "+p1f+"\n"+player2.name+": "+p2f);
        }
    }


}

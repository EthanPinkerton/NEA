package game;

import java.util.Arrays;
import java.util.Random;

public class Seeder {
    protected final char[] seed;
    protected int index;
    protected int sum;
    protected int[] stats = new int[4];

    public Seeder() {
        //this.seed = newSeed();
        this.seed = "hsujla83gh".toCharArray();
        this.index = 3;
        this.sum = 1;
    }

    private char[] newSeed(){
        Random random = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        char[] newSeed = new char[10];
        for (int i = 0; i < newSeed.length; i++) {
            newSeed[i] = chars[random.nextInt(chars.length)];
        }
        return newSeed;
    }

    public String getSeed(){
        return Character.toString(seed[0])+seed[1]+seed[2]+seed[3]+seed[4]+seed[5]+seed[6]+seed[7]+seed[8]+seed[9];
    }

    public void reset(){
        index = 3;
        sum = 1;
    }

    public int three(int prompt){
        return (seed[0]+prompt)%3;
    }

    public int two(int prompt){
        return (seed[1]*3+prompt*4)%2;
    }

    public int five(int prompt){
        return ((seed[2]-prompt+255)*23)%5;
    }

    public int four(int prompt){
        prompt = Math.abs(prompt) + 4*index;
        index ++;
        if (index > 9){
            index = 3;
        }
        sum += (int)Math.sqrt(seed[index]*prompt + (sum/index)*seed[index]);
        stats(sum%4);
        return sum%4;
    }

    private void stats(int numb){
        stats[numb] += 1;
    }

    public void printStats(){
        System.out.println("0 - "+stats[0]+"\n1 - "+stats[1]+"\n2 - "+stats[2]+"\n3 - "+stats[3]);
    }
}

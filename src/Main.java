import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(findValidGame(getInput()));
        System.out.println(getPowerSum(getInput()));
    }
    public static ArrayList<String> getInput() throws FileNotFoundException {
        Scanner inputReader = new Scanner(new File("input"));
        ArrayList<String> input = new ArrayList<>();
        while(inputReader.hasNextLine()){
            input.add(inputReader.nextLine());
        }
        return input;
    }
    public static int findValidGame(ArrayList<String> input){
        final int[] sum = {0};
        input.forEach(i ->{
            String[] gameInfo = i.split(":");
            String[] sets = gameInfo[1].split(";");
            int gameNumber = Integer.parseInt(gameInfo[0].split(" ")[1]);
            ArrayList<String> picks = new ArrayList<>();
            for(String set: sets){
                picks.addAll(Arrays.asList(set.split(",")));
            }
            picks.replaceAll(s -> s.substring(1));
            AtomicBoolean isOk = new AtomicBoolean(true );
            picks.forEach(x ->{
                String[] info = x.split(" ");
                int amount = Integer.parseInt(info[0]);
                String color = info[1];
                switch(color){
                    case "red" -> {
                        if(amount > 12) isOk.set(false);
                    }
                    case "blue" -> {
                        if(amount > 14) isOk.set(false);
                    }
                    case "green" -> {
                        if(amount > 13) isOk.set(false);
                    }
                }
            });
            if(isOk.get()) sum[0] += gameNumber;
        });
        return sum[0];
    }
    public static int getPowerSum(ArrayList<String> input){
        int sum = 0;
        ArrayList<Integer> powers = new ArrayList<>();

        input.forEach(i -> {
            String[] gameInfo = i.split(":");
            String[] sets = gameInfo[1].split(";");
            ArrayList<String> picks = new ArrayList<>();
            for(String set: sets){
                picks.addAll(Arrays.asList(set.split(",")));
            }
            picks.replaceAll(s -> s.substring(1));

            int redMax = 0;
            int blueMax = 0;
            int greenMax = 0;

            for(String pick: picks){
                String[] info = pick.split(" ");
                int amount = Integer.parseInt(info[0]);
                String color = info[1];
                switch(color){
                    case "red" -> {
                       if(amount > redMax) redMax = amount;
                    }
                    case "blue" -> {
                        if(amount > blueMax) blueMax = amount;
                    }
                    case "green" -> {
                        if(amount > greenMax) greenMax = amount;
                    }
                }
            }
            powers.add(redMax*blueMax*greenMax);
        });
        for(Integer i: powers){
            sum += i;
        }

        return sum;
    }
}
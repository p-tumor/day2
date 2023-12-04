import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final int[] sum = {0};
        Scanner inputReader = new Scanner(new File("input"));
        ArrayList<String> input = new ArrayList<>();
        while(inputReader.hasNextLine()){
            input.add(inputReader.nextLine());
        }
        input.forEach(i ->{
            String[] gameInfo = i.split(":");
            String[] sets = gameInfo[1].split(";");
            int gameNumber = Integer.parseInt(gameInfo[0].split(" ")[1]);
            ArrayList<String> picks = new ArrayList<>();
            for(String set: sets){
                picks.addAll(Arrays.asList(set.split(",")));
            }
            picks.replaceAll(s -> s.substring(1));
            AtomicBoolean isOk = new AtomicBoolean(false);
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
        System.out.println(sum[0]);
    }
}
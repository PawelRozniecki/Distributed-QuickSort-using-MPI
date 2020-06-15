package pl.pwr;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class ListGenerator {

    public static int LENGTH = 10000000;
    public void generate() throws IOException {
        Random random = new Random();
        String fileName = Integer.toString(LENGTH);
        FileWriter file =  new FileWriter(fileName+".txt");

        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0;i<LENGTH;i++){
            a.add(random.nextInt(LENGTH*2));
        }

        for(int list: a){
            file.write(list+"\n");

        }

        file.close();
    }
}

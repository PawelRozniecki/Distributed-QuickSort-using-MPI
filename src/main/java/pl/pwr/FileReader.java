package pl.pwr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader  {


    public int[] loadFile(File file)throws IOException {

        Scanner scanner = new Scanner(file);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(scanner.hasNextInt()){
            arrayList.add(scanner.nextInt());
        }
        Object[] returnArrayObject = arrayList.toArray();
        int[] returnArr = new int[returnArrayObject.length];

        for(int i =0; i< returnArrayObject.length;i++){
            returnArr[i] = (Integer) returnArrayObject[i];
        }

        return  returnArr;
    }





}

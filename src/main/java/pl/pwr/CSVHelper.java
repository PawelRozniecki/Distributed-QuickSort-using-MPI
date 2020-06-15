package pl.pwr;

import java.io.*;

public class CSVHelper {


    private static final char DEFAULT_SEPARATOR = ',';


    public void convertArrayToCSV(int[] array,int numCPU, long execTime) throws IOException {
        //using buffered writer because it's more efficient
        File file = new File("/Users/pingwin/IdeaProjects/bruh/result.csv");
        FileWriter fileWriter = new FileWriter(file,true);
        StringBuilder sb = new StringBuilder();
        sb.append("size of array: ").append(array.length).append(" Number of processors: ")
                .append(numCPU).append(" Execution time: ").append(execTime).append("ms")
                .append("\n");

        for(int num: array){
            sb.append(num);
            sb.append(DEFAULT_SEPARATOR);
        }
        sb.append("\n");
        fileWriter.write(sb.toString());
        fileWriter.close();

    }
}

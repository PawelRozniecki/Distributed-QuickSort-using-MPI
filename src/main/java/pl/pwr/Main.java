package pl.pwr;

import mpi.MPI;
import  pl.pwr.ListGenerator;
import pl.pwr.QuickSort;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
public class Main {


    void main(String... args) throws IOException {
        QuickSort q = new QuickSort();
        CSVHelper helper =new CSVHelper();


//        ListGenerator listGenerator = new ListGenerator();
//        listGenerator.generate();

        FileReader f = new FileReader();
        File file = new File("/Users/pingwin/IdeaProjects/bruh/src/main/java/pl/pwr/7500.txt");
        f.loadFile(file);
        int[] arr = f.loadFile(file);

           //initialize MPI

            MPI.Init(args);
            int root = 0;
            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();
            int length = arr.length-1;

            //length of one process
            int unitSize = length/size;

            //this is what we try to send from root node
            int[] sendBuffer = new int[length];

            //values that we recieve from the root node
            int[] recieveBuffer = new int[unitSize];

            //values that root recieves from all the nodes
            int[] newRecieveBuffer = new int[length];


        if(rank == root){
            sendBuffer = arr;
//            System.out.printf("Unsorted: %s \n", Arrays.toString(sendBuffer));
        }

            //start measuring time
            long startTime = System.currentTimeMillis();
        long nanoStart = System.nanoTime();

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT, recieveBuffer, 0, unitSize, MPI.INT, root);

            //try to sort in each process
            recieveBuffer = q.quickSort(recieveBuffer,0, recieveBuffer.length-1);
            //gather all the parts into one array

        MPI.COMM_WORLD.Gather(recieveBuffer, 0, unitSize, MPI.INT, newRecieveBuffer, 0, unitSize, MPI.INT, root);

        System.out.println();

        //when all processes finish sorting do one last sort to return final array

        if(rank == root){
            int[] finalSortedList = q.quickSort(newRecieveBuffer, 0, newRecieveBuffer.length-1);
            long stopTimer = System.currentTimeMillis();
            long nanoStop = System.nanoTime();
            long execTime = stopTimer - startTime;
//           helper.convertArrayToCSV(finalSortedList,size, execTime);
            System.out.printf("Time it took: %d ms\n", execTime);
            System.out.printf("Time it took: %d nano second\n", nanoStop - nanoStart);
            
        }

        MPI.Finalize();
    }


}

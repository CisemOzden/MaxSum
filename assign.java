import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
 
  
class assign { 

    /* maxSum assumes  the input is in the form of
    X 0 0 0 ..0
    X X 0 0 ..0
    .
    .
    X X X X ..X
    starts checking the numbers from bottom,
    for each element, adds the greater of the below and below right numbers 
    (it checks the primeness at the same time)
    Number at the topmost left gives the result
    */
    static int maxSum(int pyr[][], int n){ 
        boolean lowest_row=true;
        for (int i = n - 1; i >= 0; i--){ 
            for (int j = 0; j <= i; j++){ 
                if(lowest_row){ //if we are in the lowest row, we should check both the numbers in the lowest row and the row above
                    if(!isPrime(pyr[i][j])){
                        List<Integer> temp = new ArrayList<Integer>();
                        if(!isPrime(pyr[i + 1][j])) temp.add(pyr[i + 1][j]); //the number in the below is checked
                        if(!isPrime(pyr[i + 1][j+1])) temp.add(pyr[i+1][j+1]); //the number in the below right is checked

                        if(!temp.isEmpty()){
                            pyr[i][j] += Collections.max(temp);
                        }
    
                    }
                    else{ //the number is not considered if it is not prime
                        pyr[i][j]=0;
                    }
                    
                }
                else{ //we should only check if the numbers in the current row are prime or not
                    if(!isPrime(pyr[i][j])){

                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(pyr[i + 1][j]);
                        temp.add(pyr[i + 1][j + 1]);

                        if(!temp.isEmpty()){
                        pyr[i][j] += Collections.max(temp);
                        }

                    }
                    else{
                        pyr[i][j]=0;
                    }
                }  
            }
            System.out.println(); 
            lowest_row=false;
        } 
      
        return pyr[0][0]; 
    } 

    static boolean isPrime(int n){
        if(n<2){
            return false;
        }
        if(n==2){
            return true;
        }
        for(int i=2; i*i<=n; i++){
            if(n%i==0){
                return false;
            }
        }
        return true;  
    } 
      
    public static void main (String[] args){

        ArrayList<String> numbers = new ArrayList<String>();
        int N=0;

        //This block is for reading the numbers
        try{
            File f = new File("text.txt"); //write the name of the text file containing pyramid
            Scanner scanner2 = new Scanner(f);
            while(scanner2.hasNextLine()){
                int m=0;
                N++;
                String line = scanner2.nextLine();
                for(m=0; m<line.length(); m++){
                    if(line.charAt(m)!=' '){
                        break;
                    }
                }
                String [] arr = line.substring(m).split(" ");

                for(int i=0; i<arr.length; i++){
                    numbers.add(arr[i]);  //appends the every number in the pyramid to an ArrayList
                }
            }
            scanner2.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int [][] matrix= new int [N][N];

         int k=0;
            for(int i=0; i<N; i++){
                for(int j=0; j<i+1; j++){
                    matrix[i][j] = Integer.parseInt(numbers.get(k)); //transfers the numbers in the ArrayList to the desired array
                    k++;
                }
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }

        System.out.println(maxSum(matrix, N-1));
    } 
} 
  

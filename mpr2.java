import java.util.Scanner;

class MatrixOperations {
    
     public void inputMatrix(Scanner sc, float[][] Matrix) {
        int rows = Matrix.length;
        int cols = Matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Enter transition probability from state " + i + " to state " + j);
                Matrix[i][j] = sc.nextFloat();
            }
        }
    }

     public void initialMat(Scanner sc, float[][] Matrix) {
        int rows = Matrix.length;
        int cols = Matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the value for cell (" + (i + 1) + ", " + (j + 1) + "): ");
                Matrix[i][j] = sc.nextFloat();
            }
        }

     double total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += Matrix[i][j];
            }
        }

        if (total > 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Matrix[i][j] /= total;
                }
            }
        }

    }

    public float[][] multiplyMat(float[][] MatA, float[][] MatB) {
        int rowsA = MatA.length;
        int colsA = MatA[0].length;
        int rowsB = MatB.length;
        int colsB = MatB[0].length;

        if (colsA != rowsB) {
            System.out.println("Matrix Multiplication is not possible. Please make sure that cols of the first matrix are equal to rows of the second matrix");
            return null;
        }

        float[][] ResultMat = new float[rowsA][colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                ResultMat[i][j] = 0.0f;
                for (int k = 0; k < colsA; k++) {
                    ResultMat[i][j] += MatA[i][k] * MatB[k][j];
                }
            }
        }

        return ResultMat;
}
     
///check if all elements of all rows are equal
// this is condition for stability matrix

    public int check(float[][] Matrix){
     int rows = Matrix.length;
     int cols = Matrix[0].length;
     int count =0;
      for (int i = 0; i < rows; i++) {
        float rowElement = Matrix[i][0]; // Get the first element of the row

        for (int j = 1; j < cols; j++) {
            if (Matrix[i][j] != rowElement) {
                count=0;
            }else {
                count++;
            }
        }
    }

    if (count!=0){
    return 1;
    }
    else
     return 0;

}

//used to calculate total of input taken from matrix B

public float calcTotal (float Matrix[][]){
     int rows = Matrix.length;
     int cols = Matrix[0].length;
      float total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += Matrix[i][j];
            }
        }
   return total;

}

// convert output probablity into solid values

public float[][] output (float Matrix[][], float total){
       int rows = Matrix.length;
       int cols = Matrix[0].length;

       for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
               Matrix[i][j] = Matrix[i][j] * total;
            }
        }

    return Matrix;

}


    public void displayMatrix(float[][] Matrix) {
        int rows = Matrix.length;
        int cols = Matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public  float[][] powerMat(float[][] Matrix, int n) {
        //int rows = Matrix.length;
       // int cols = Matrix[0].length;
        int i = 1;
        float[][] ResultN = Matrix;
        while (i < n) {
            
            ResultN = multiplyMat(ResultN, Matrix);
            if (check(ResultN)==1){
                System.out.println(" the stability matrix is achieved when value of n is " +  i);
                break;
            }
            i++;
        }
        return ResultN;
    }

    public static boolean isColumnSum(float[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int j = 0; j < numCols; j++) {
            float columnSum = 0.0f;
            for (int i = 0; i < numRows; i++) {
                columnSum += matrix[i][j];
            }
            if (Math.abs(columnSum - 1.0f) > 1e-6) {
                System.out.println("No. Column " + j + " does not sum to 1.0.");
                return false;
            }
        }
        System.out.println("Yes. All columns sum to 1.0.");
        return true;
    }
}

public class mpr2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows for the probability matrix: ");
        int rowsA = sc.nextInt();

        System.out.println("Enter the number of columns for the probability matrix: ");
        int colsA = sc.nextInt();

        if (rowsA == colsA) {

            System.out.println("Enter the number of rows for the initial condition: ");
            int rowsB = sc.nextInt();

            System.out.println("Enter the number of columns for the initial condition: ");
            int colsB = sc.nextInt();

            if (colsA!=rowsB){
             System.out.println("Matrix multiplication is not possible. Make sure that columns of probablity matrix is equal to rows of initial matrix.");
             }
             else{

            float[][] MatA = new float[rowsA][colsA];
            float[][] MatB = new float[rowsB][colsB];

            System.out.println("Enter the probability matrix: ");
            MatrixOperations ob1 = new MatrixOperations();
            ob1.inputMatrix(sc, MatA);
            boolean isMatAValid = MatrixOperations.isColumnSum(MatA);

            System.out.println("Enter initial conditions: ");
            ob1.initialMat(sc, MatB);
         

            if (isMatAValid) {
                System.out.println("Enter the number of times: ");
                int n = sc.nextInt();

                float[][] ResultN = ob1.powerMat(MatA, n);

                float[][] Resultmat = ob1.multiplyMat(ResultN, MatB);
                
                float tot = ob1.calcTotal(MatB);

                float [][] outputMat= ob1.output(Resultmat, tot);

                System.out.println("Values after n: ");

                ob1.displayMatrix(outputMat);

                boolean valid = MatrixOperations.isColumnSum(Resultmat);
                if (valid){
                    System.out.println("the result matrix is stochastic");
                }
                else {
                     System.out.println("the result matrix is not stochastic");
                }

            }
             
       
          } 
         }else {
            System.out.println("Please enter a square matrix for the probability matrix ");
        }

        sc.close();
    }
}

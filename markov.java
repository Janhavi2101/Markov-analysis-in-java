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

    public  void displayMatrix(float[][] Matrix) {
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
        int rows = Matrix.length;
        int cols = Matrix[0].length;
        int i = 1;
        float[][] ResultN = Matrix;
        while (i < n) {
            
            ResultN = multiplyMat(ResultN, Matrix);
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

public class markov {
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
            ob1.inputMatrix(sc, MatB);
            boolean isMatBValid = MatrixOperations.isColumnSum(MatB);

            if (isMatAValid && isMatBValid) {
                System.out.println("Enter the number of times: ");
                int n = sc.nextInt();
                   MatrixOperations ob2 = new MatrixOperations();
                float[][] ResultN = ob2.powerMat(MatA, n);

                   MatrixOperations ob3 = new MatrixOperations();
                float[][] Resultmat = ob3.multiplyMat(ResultN, MatB);

                System.out.println("Probability after n: ");
                   MatrixOperations ob4 = new MatrixOperations();
                ob4.displayMatrix(Resultmat);

            }
             
       
          } 
         }else {
            System.out.println("Please enter a square matrix for the probability matrix ");
        }

        sc.close();
    }
}

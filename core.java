//Solves the system linear System (A|b) using swaping, multiplying and adding of Rows
//Tested until up to 4x4 matrices
//Use for uniquely solvable matrices
import java.util.Arrays;


public class MatrixSolver {
	
	private static void addRow(double[][][] m, int i, int j){		 //adds Row i to Row j
		for (int s = 0; s< m[0][1].length ;s++){							
			m[0][j][s] = m[0][j][s]+m[0][i][s];										//adds Row i to j in A
		}
		m[1][0][j] = m[1][0][j]+ m[1][0][i];
		}
			 
	private static void multiplyRow(double[][][] m, int i, double l){		//multiplys Row i with l
		for(int s = 0; s < m[0][1].length; s++){
			m[0][i][s] = l* m[0][i][s];
		}
		m[1][0][i] = l*m[1][0][i];
	}
	
	public static int findPivot(double[][][] m, int j){				//Findet das Pivot Element der j-ten Spalte
		int n = 0;
		for (int  i = 0; i < m[0].length; i++){
			if (m[0][i][j] != 0){
				n = i;
			}
		}
		return n;
	}
	
	public static void solveColumn(double[][][] m, int j, int p){    //Loest die j-Spalte mithilfe der Zeile des Pivot Elemnts p
		
			multiplyRow(m,p,1/m[0][p][j]);
				for (int s = 0; s < m[0].length ; s++){
					if(m[0][s][j] !=0 && s !=p){
						double d = -1/m[0][s][j];
						multiplyRow(m,s,d);
						addRow(m,p,s);
					}
				}
	}
	//Loest die Matrix
	public static void solveMatrix(double[][][] m){
		int s = 0;
		for(int i = 0; i < m[0][0].length;i++){
			
			if (m[0][s][i] == 0){
				while (m[0][s][i] == 0){
					s++;
				}
			}
			solveColumn(m, i, s);
			s++;
		}
		s = s -1;
		for (int i = s; i>=0;i--){
			multiplyRow(m,i,1/m[0][i][i]);
		}
	}
	
	
	public static void main (String args[]){
		double[][] matrix1  = {{1,2,3},{3,500,5},{6,7,20}};    //A1
		double[][] matrix2  = {{2,10,15}};				//b1
		double[][][] matrix = {matrix1, matrix2};			//(A|b)1
		solveMatrix(matrix);
		
		double[][] matrix11  = {{1,2,3,4},{5,6,71,8},{9,12,11,12},{13,14,15,16}};    //A2
		double[][] matrix21 = {{1,2,3,4}};				//b2
		double[][][] matrix31 = {matrix11, matrix21};			//(A|b)2
		solveMatrix(matrix31);
		

		//Tests of single methods
		//System.out.print(Arrays.deepToString(matrix));
		//System.out.print(matrix[0][0][1]);
		//System.out.println(Arrays.deepToString(matrix));
		//System.out.println(matrix[0][0][1]);
		//System.out.println(Arrays.deepToString(addRow(matrix, 1, 2)));
		//System.out.println(Arrays.deepToString(multiplyRow(matrix, 1, 5)));
		//System.out.println(findPivot(matrix,0));
		//System.out.println(Arrays.deepToString(solveColumn(matrix, 0 , 0)));
		//System.out.println(Arrays.deepToString(solveColumn(matrix, 1 , 1)));
		//System.out.println(Arrays.deepToString(solveColumn(matrix, 2, 2)));
		

		System.out.println(Arrays.deepToString(matrix)); //prints out solved matrix    (A|b)1
		System.out.println(Arrays.deepToString(matrix[1])); //Prints solution only     b
		
		System.out.println(Arrays.deepToString(matrix31[1])); //Testing another matrix   (A|b)2
	}
	
	
	
}


public class sandbag1 {
	public static int[][] values = new int[3][3];

	public static boolean[] isNumberAvailable = new boolean[9];    

	public static void main(String [] args) {


		for(int i=0; i<3*3; i++)
			isNumberAvailable[i]=true;

		for (int row = 0; row < 3; row++) 
		{
			for (int col = 0; col < 3; col++) 
				values[row][col] = 0;  
		}

		int testSum=0;

		makeAValidMagicSquaresRow(0);
		makeAValidMagicSquaresRow(1);
		makeAValidMagicSquaresRow(2);

	}

	public static void makeAValidMagicSquaresRow(int row){
		int testSum=0;

		int testNum = (1 + (int)((9-1+1) * Math.random()));


		while(testSum!=15)
		{
			testSum=0;
			for(int i=0; i<3*3; i++)
				isNumberAvailable[i]=true;

			for (int i = 0; i < 3; i++) 
			{
				for (int col = 0; col < 3; col++) 
					values[i][col] = 0;  
			}


			for(int i=0; i<3; i++)
			{
				do{
					if(isNumberAvailable[testNum-1]){
						values[row][i]=testNum;
						isNumberAvailable[testNum-1]=false;
						testSum+=testNum;
					}

					else
						testNum = (1 + (int)((9-1+1) * Math.random()));

				} while(!isNumberAvailable[testNum-1]);
				System.out.println("The numbers in the first row are: " + values[row][i]);
			}
			System.out.println("This row adds up to " + testSum);
		}			


		System.out.println();
	}
}

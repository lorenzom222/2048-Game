
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class project1 {
	static int moveCount = 0;
	static int score = 0;

	public static void main(String[] args) {
		System.out.println("2048!");
		System.out.println("Press a(R), d(L), w(U), or s(D):");
		System.out.println("q to Quit, r to Restart");

		int[][] intArray = new2048(); // calls clear board with 2 random vals

		outerloop: while (!checkIfGameOver(intArray)) {// will continue until that method is true
			score = 0;
			for (int i = 0; i < intArray.length; ++i) {// the max # on board is high score
				for (int j = 0; j < intArray[0].length; ++j) {
					if (score < intArray[i][j])
						score = intArray[i][j];
				}
			}
			for (int i = 0; i < intArray.length; ++i) {// scans for 2048 to win
				for (int j = 0; j < intArray[0].length; ++j) {
					if (intArray[i][j] == 2048) {
						System.out.println("You win");

						break outerloop;// when this is reached, it will break out of every loop in
					}
				}
			}
			System.out.println("Moves: " + moveCount);
			System.out.println("Score: " + score);
			direction(intArray);
		}
		System.out.println("Game Over");

	}

	public static int[][] new2048() {// creates clear board and places two random values
		int[][] array = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }

		};
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                array[i][j] = 0;
            }
        }
		randomVal(array);
		randomVal(array);
		print2Darray(array);
		return array;

	}

	public static void print2Darray(int[][] array) {// pass in integer array to print
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}

	}

	public static int[][] direction(int[][] array) {// switch cases for each user input
		String dir;
		Scanner scnr = new Scanner(System.in);
		dir = scnr.next();
		switch (dir) {
		case "a":
			moveCount++;// everytime these keys are used, moveCount increases
			swapL(array);
			System.out.println("Valid input");

			break;

		case "d":
			moveCount++;

			swapR(array);
			System.out.println("Valid input");

			break;

		case "w":
			moveCount++;

			swapU(array);
			System.out.println("Valid input");

			break;

		case "s":
			moveCount++;

			swapD(array);
			System.out.println("Valid input");

			break;

		case "q":
			System.out.println("You sure you wanna quit? y for Yes. n for No");
			String quit = scnr.next();

			switch (quit) {// nested switch cases to make choice

			case "y":
				System.out.println("You quit");
				System.exit(0);// terminates program if reached
				break;

			case "n":
				direction(array);
				break;
			}
			break;

		case "r":
			System.out.println("You sure you wanna restart? y for Yes. n for No");
			String restart = scnr.next();

			switch (restart) {

			case "y":
				moveCount = 0;
				score = 0;

				new2048();// calls method which clears board and place 2 random values
				break;

			case "n":
				direction(array);
				break;
			}
			break;

		default:
			System.out.println("Not Valid input");// if irrelevant key is pressed

		}
		return array;

	}

	public static int[][] randomVal(int[][] array) {
		ArrayList<int[]> list = new ArrayList<int[]>();

		for (int i = 0; i < array.length; ++i) {
			for (int j = 0; j < array[0].length; ++j) {
				if (array[i][j] == 0) {// sort every zero into temp list
					int[] temp = { i, j };
					list.add(temp);
				}
			}
		}

		int numFreeIndexes = list.size();// keeps track of all 0s
		Random rand = new Random();

		if (numFreeIndexes != 0) {// if there are any 0s in temp list
			int chosenIndex = rand.nextInt(numFreeIndexes);// will pick random 0 from list
			int[] indexPair = list.get(chosenIndex);// then put into cooridindates
			int indexI = indexPair[0];
			int indexJ = indexPair[1];
			int twoOrFour = rand.nextInt(5) + 1;// this creates probability. 2/10=1/5

			if (twoOrFour > 4)// if 5, then will print 4
				array[indexI][indexJ] = 4;// 1/5=20%

			else
				array[indexI][indexJ] = 2;// 4/5=80%
		}
		return array;

	}

//	Will swap the values of the element to the right of 0, only if the column is less than 3

	public static int[][] swapR(int[][] array) {// very importan, uses Bubble sort to move elements
		for (int i = 0; i < array.length; i++) {// This will look at each element and switch with 0 next to it
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) { // implemented 3 for loops to ensure no floating or left behind values
			for (int j = 0; j < array.length - 1; j++) {// bc each for loop only runs through once
				if (array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		for (int i = 0; i < array.length; i++) {// this will scan in the opposite direction to merge the farthest
												// element in direction
			for (int j = 3; j > 0; j--) {
				if (array[i][j] == array[i][j - 1]) {
					array[i][j] *= 2;// element looking at will double
					array[i][j - 1] = 0;// one behind it will be remove, or just be set to 0

				}

			}

		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j + 1] == 0) {
					array[i][j + 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		randomVal(array);

		print2Darray(array);

		return array;
	}
//	Will swap the values of the element to the left of 0, only if the column is greater than 0

	public static int[][] swapL(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 3; j > 0; j--) {
				if (array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 3; j > 0; j--) {
				if (array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 3; j > 0; j--) {
				if (array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[i][j] == array[i][j + 1]) {
					array[i][j] *= 2;
					array[i][j + 1] = 0;

				}

			}

		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 3; j > 0; j--) {
				if (array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = 3; j > 0; j--) {
				if (array[i][j - 1] == 0) {
					array[i][j - 1] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		randomVal(array);

		print2Darray(array);

		return array;
	}

//	Will swap the values of the element above the 0, only if the row above is greater than 0
	public static int[][] swapU(int[][] array) {
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == 0) {
					array[i - 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == 0) {
					array[i - 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == 0) {
					array[i - 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == array[i][j]) {
					array[i + 1][j] *= 2;
					array[i][j] = 0;

				}

			}

		}

		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == 0) {
					array[i - 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == 0) {
					array[i - 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		randomVal(array);

		print2Darray(array);

		return array;
	}

//	Will swap the values of the element below the 0, only if the row above is less than 3
	public static int[][] swapD(int[][] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}

		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < array.length; j++) {
				if (array[i - 1][j] == array[i][j]) {
					array[i - 1][j] *= 2;
					array[i][j] = 0;

				}

			}

		}

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i + 1][j] == 0) {
					array[i + 1][j] = array[i][j];
					array[i][j] = 0;

				}

			}
		}
		randomVal(array);

		print2Darray(array);

		return array;

	}

	public static boolean checkIfGameOver(int[][] array) { // if true will end game
		boolean gameOver = false;

		int rightCheck = 0;// if all of these counters stay at zero, then gameOver will return true and end
							// game
		int leftCheck = 0;
		int upCheck = 0;
		int downCheck = 0;
		int count = 0;

		int i;
		int j;

		for (i = 0; i < array.length; ++i) {// first checks if board is empty
			for (j = 0; j < array[0].length; ++j) {
				if (array[i][j] == 0) {
					count++;
				}
			}
		}
		if (count == 0) {// if empty, then will check if there are no element equal to each that are near

			for (i = 0; i < array.length; i++) {
				for (j = 3; j > 0; j--) {
					if (array[i][j] == array[i][j - 1]) {
						rightCheck++;

					}

				}

			}
			for (i = 0; i < array.length; i++) {
				for (j = 0; j < array.length - 1; j++) {
					if (array[i][j] == array[i][j + 1]) {
						leftCheck++;

					}

				}

			}
			for (i = 0; i < array.length - 1; i++) {
				for (j = 0; j < array.length; j++) {
					if (array[i + 1][j] == array[i][j]) {
						upCheck++;

					}

				}

			}
			for (i = array.length - 1; i > 0; i--) {
				for (j = 0; j < array.length; j++) {
					if (array[i - 1][j] == array[i][j]) {
						downCheck++;

					}

				}

			}

			if (rightCheck == 0 && leftCheck == 0 && upCheck == 0 && downCheck == 0) {// no counter goes up, returns
																						// true and game ends in main
				gameOver = true;

			}
		}
		return gameOver;
	}

}

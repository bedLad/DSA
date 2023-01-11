public class Main {
  private static boolean IsSafe(int[][] sudoku, int row, int col, int digit) {
    // column
    for(int i=0; i<=8; ++i) {
      if(sudoku[i][col] == digit)
        return false;
    }
    
    // row
    for(int j=0; j<=8; ++j) {
      if(sudoku[row][j] == digit)
        return false;
    }
    
    // grid
    int startRow=(row/3)*3, startCol=(col/3)*3;
    for(int i=startRow; i<=startRow+2; ++i) {
      for(int j=startCol; j<=startCol+2; ++j) {
        if(sudoku[i][j] == digit)
          return false;
      }
    }
    
    return true;
  }
  
  private static boolean SudokuSolver(int[][] sudoku, int row, int col) {
    // base case
    if(row == 9 && col == 0)
      return true;
    
    // logic
    int nextRow=row, nextCol=col+1;
    if(col+1 == 9) {
      nextRow = row+1;
      nextCol = 0;
    }
    
    if(sudoku[row][col] != 0)
      return SudokuSolver(sudoku, nextRow, nextCol);
    
    for(int digit=1; digit<=9; ++digit) {
      if(IsSafe(sudoku, row, col, digit)) {
        sudoku[row][col] = digit;
        if(SudokuSolver(sudoku, nextRow, nextCol))
          return true;
        
        sudoku[row][col] = 0;
      }
    }
    return false;
  }
  
  private static void Print(int sudoku[][]) {
    for(int i=0; i<9; ++i) {
      for(int j=0; j<9; ++j)
        System.out.print(sudoku[i][j] + " ");
      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    int sudoku[][] = {
      {0, 0, 8, 0, 0, 0, 0, 0, 0},
      {4, 9, 0, 1, 5, 7, 0, 0, 2},
      {0, 0, 3, 0, 0, 4, 1, 9, 0},
      {1, 8, 5, 0, 6, 0, 0, 2, 0},
      {0, 0, 0, 0, 2, 0, 0, 6 ,0},
      {9, 6, 0, 4, 0, 5, 3, 0, 0},
      {0, 3, 0, 0, 7, 2, 0, 0, 4},
      {0, 4, 9, 0, 3, 0, 0, 5, 7},
      {8, 2, 7, 0, 0, 9, 0, 1, 3}
    };
    SudokuSolver(sudoku, 0, 0);
    Print(sudoku);
  }
}

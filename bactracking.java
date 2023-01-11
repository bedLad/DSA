import java.util.*;

public class Main {
  static int count=0;
  private static void Permute(String str, String ans) {
    if(str.length() == 0) {
      System.out.println(ans);
      return;
    }
    for(int i=0; i<str.length(); ++i) {
      char ch = str.charAt(i);
      String newStr = str.substring(0,i)+str.substring(i+1);
      Permute(newStr, ans+ch);
    }
    return;
  } 
  private static void RecPermute(String str, String ans, int idx) {
    if(str.length() == 0) {
      System.out.println(ans);
      return;
    }  
    char ch = str.charAt(idx);
    String newStr = str.substring(0,idx)+str.substring(idx+1);
    RecPermute(newStr, ans+ch, 0);
    if(str.length() > idx+1)
      RecPermute(str, ans, idx+1);
    return;
  }
  private static void Print(char board[][]) {
    for(int i=0; i<board.length; ++i) {
      for(int j=0; j<board[0].length; ++j)
        System.out.print(board[i][j]+" ");
      System.out.println();
    }
    System.out.println();
      
  }  
  private static boolean nQueen(char board[][], int row) {
    if(row == board.length) {
      Print(board);
      ++count;
      return true;
    }
      
    for(int col=0; col<board.length; ++col) {
      if(IsSafe(board, row, col)) {
        board[row][col] = 'Q';
        if(nQueen(board, row+1))
          return true;
        board[row][col] = '_';
      }
    }
    
    return false;
  }  
  private static boolean IsSafe(char[][] board, int row, int col) {
    // vertical up
    for(int i=row-1; i>=0; --i) {
      if(board[i][col] == 'Q')
        return false;
    }
    // diag left up
    for(int i=row-1, j=col-1; i>=0&&j>=0; --i, --j) {
      if(board[i][j] == 'Q')
        return false;
    }
    // diag right up
    for(int i=row-1, j=col+1; i>=0&&j<board.length; --i, ++j) {
      if(board[i][j] == 'Q')
        return false;
    }
    
    return true;
  }  
  private static int Grid(char[][] grid, int row, int col) {
    // base case
    if(row == grid.length-1 || col == grid[0].length-1) {
      return 1;
    }
    // logic
    return (Grid(grid, row, col+1) + Grid(grid, row+1, col));
  }
  private static void Mazeprint(int[][] board) {
    for(int i=0; i<board.length; ++i) {
      for(int j=0; j<board[0].length; ++j)
        System.out.print(board[i][j]+" ");
      System.out.println();
    }
    System.out.println();
  }
  
  // Rat in a maze
  private static boolean RatInMaze() {
    int[][] maze = {
      {1,1,1,0},
      {0,1,1,0},
      {1,1,1,0},
      {1,1,1,1}
    };
    
    int[][] sol = {
      {0,0,0,0},
      {0,0,0,0},
      {0,0,0,0},
      {0,0,0,0}
    };
    if(!RatInMazeUtils(maze, 0, 0, sol)) {
      System.out.println("Solution doesn't exist");
      return false;
    } else {
      Mazeprint(sol);  
      return true;
    }
  }
  private static boolean Valid(int[][] maze, int row, int col) {
    if(row>=0 && row<maze.length && col>=0 && col<maze[0].length && maze[row][col] == 1)
      return true;
    else 
      return false;
  }
  private static boolean RatInMazeUtils(int[][] maze, int row, int col, int[][] sol) {
    // base case
    if(row == maze.length-1 && col == maze[0].length-1 && maze[row][col] == 1) {
      sol[row][col] = 1;
      return true;
    }
      
    // logic
    if(Valid(maze, row, col)) {
      sol[row][col] = 1;
      if(RatInMazeUtils(maze, row+1, col, sol))
        return true;
      if(RatInMazeUtils(maze, row, col+1, sol))
        return true;
      sol[row][col] = 0;
    }
    return false;
  }
  
  // Knight movement on chessboard
  private static boolean SolveKnight() {
    int[][] sol = new int[8][8];
    for(int x=0; x<8; ++x)
      for(int y=0; y<8; ++y)
        sol[x][y] = -1;
    
    sol[0][0] = 0;
    int[] xmove = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] ymove = {1, 2, 2, 1, -1, -2, -2, -1};
    
    if(!solveKnightUtils(sol, 0, 0, 1, xmove, ymove)) {
      System.out.println("Solution not exists");
      return false;
    }
    else {
      Mazeprint(sol);
      return true;
    }
  }
  private static boolean IsValid(int[][] sol, int x, int y) {
    if(x>=0 && y>=0 && x<8 && y<8 && sol[x][y]==-1)
      return true;
    else
      return false;
  }
  private static boolean solveKnightUtils(int[][] sol, int x, int y, int movei, int[] xmove, int[] ymove) {
    // base case
    if(movei == 8*8)
      return true;
    
    // logic
    for(int k=0; k<sol.length; ++k) {
      int nextX=x+xmove[k], nextY=y+ymove[k];
      if(IsValid(sol, nextX, nextY)) {
        sol[nextX][nextY] = movei;
        if(solveKnightUtils(sol, nextX, nextY, movei+1, xmove, ymove)) 
          return true;
        else
          sol[nextX][nextY] = -1;
      }
    }
    return false;
  }
  
  // Keypad problem
  private static ArrayList<String> Keypad() {
    String[] keypad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ArrayList<String> sol = new ArrayList<String>();
    return KeypadUtils("23", keypad, 0, "", sol);
  }
  private static ArrayList<String> KeypadUtils(String keys, String[] keypad, int idx, String ans, ArrayList<String> sol) {
    // base case
    if(idx == keys.length()) {
      sol.add(ans.toString());
      return sol;
    }
    // logic
    String letters = keypad[Character.getNumericValue(keys.charAt(idx))];
    for(int i=0; i<letters.length(); ++i) {
      char ch = letters.charAt(i);
      KeypadUtils(keys, keypad, idx+1, ans+ch, sol);
    }
    return sol;
  }
  
  // kth Permutation
  private static int ktracker = 0;
  private static StringBuilder sol = new StringBuilder("");
  private static StringBuilder Permutation(String str, int k, StringBuilder ans) {
    // base case
    if(str.length() == 0) {
      ktracker++;
      if(ktracker == k) 
        sol.append(ans);
      ans.setLength(ans.length()-1);
      return ans;
    } 
    // logic
    for(int i=0; i<str.length(); ++i) {
      char ch = str.charAt(i);
      String newStr = str.substring(0,i)+str.substring(i+1);
      Permutation(newStr, k, ans.append(Character.toString(ch)));
    }
    return sol;
  }
  public static StringBuilder getPermutation(int n, int k) {
    String str = "";
    for(int i=1; i<=n; ++i) {
      str += Integer.toString(i);
    } 
 
    return Permutation(str, k, new StringBuilder("")); // problem is here
  }
  
  public static void main(String[] args) {
    TnQueen();
  }
}

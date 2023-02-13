class Main {
  private static int fibo(int n, int[] f) {
    if(n==0 || n==1)
      return n;
    if(f[n] != 0) 
      return f[n];
    f[n] = fibo(n-1, f)+fibo(n-2, f);
    return f[n];
  }
  
  // climbing stairs Tabulation
  private static int tabClimbingStairs(int n) {
    int[] s = new int[n+1];
    s[0] = 1; s[1] = 1;
    
    for(int i=2; i<=n; ++i) {
      s[i] = s[i-1]+s[i-2];
    }
    
    System.out.println(s[4]);
    return s[5];
  }
  
  
  // Knapsack problem Question
  private static void knapsack() {
    int[] val = {15, 14, 10, 45, 30};
    int[] wt = {2, 5, 1, 3, 4};
    int W = 7;
    int[][] dp = new int[val.length+1][W+1];
    
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) 
        dp[i][j] = -1;
    }
    
    System.out.println(knapsackUtil(val, wt, W, 0, dp));
    System.out.println(tabKnapsack(val, wt, W));
  }
  // knapsack problem Memoization
  private static int knapsackUtil(int[] val, int[] wt, int W, int n, int[][] dp) {
    if(W==0 || n==val.length)
      return 0;
    
    if(dp[n][W] != -1)
      return dp[n][W];
    
    if(wt[n] <= W) {
      int chosen = val[n] + knapsackUtil(val, wt, W-wt[n], n+1, dp);
      int notChosen = knapsackUtil(val, wt, W, n+1, dp);
      dp[n][W] = Math.max(chosen, notChosen);
      return dp[n][W];
    } else {
      dp[n][W] = knapsackUtil(val, wt, W, n+1, dp);
      return dp[n][W];
    }
  }
  // knapsack problem Tabulation
  private static int tabKnapsack(int[] val, int[] wt, int W) {
    int n = val.length;  int[][] dp = new int[n+1][W+1];
    
    for(int i=0; i<dp.length; ++i)
      dp[i][0] = 0;
    for(int j=0; j<dp[0].length; ++j)
      dp[0][j] = 0;
    
    for(int i=1; i<n+1; ++i) {
      for(int j=1; j<W+1; ++j) {
        int v = val[i-1];
        int w = wt[i-1];
        if(w <= j) {
          int incProf = v+dp[i-1][j-w];
          int excProf = dp[i-1][j];
          dp[i][j] = Math.max(incProf, excProf);
        } else {
          int excProf = dp[i-1][j];
          dp[i][j] = excProf;
        }
      }
    }
    dpPrint(dp);
    return dp[n][W];
  } 
  private static void dpPrint(int[][] dp) {
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) {
        System.out.print(dp[i][j]+" ");
      }
        System.out.println();
    }
    System.out.println();
  }
  
  
  // Target sum subset Question
  private static void subsetSum() {
    int[] ar = {9,1,2};
    boolean[][] dp = new boolean[ar.length+1][0+1];
    
    System.out.println(subsetSumMem(ar, 0, 0, dp));
  }
  // Tabulation Target sum Subset
  private static boolean tabSubsetSum(int[] ar, int target) {
    int n = ar.length;
    boolean[][] dp = new boolean[n+1][target+1];
    for(int i=0; i<n+1; ++i) 
      dp[i][0] = true;
    
    for(int i=1; i<n+1; ++i) {
      for(int j=1; j<target+1; ++j) {
        int v = ar[i-1];
        if(v<=j && dp[i-1][j-v] == true)
          dp[i][j] = true;
        else if(dp[i-1][j] == true)
          dp[i][j] = true;
      }
    }
    return dp[n][target];
  }
  // Memoization target sum subset
  private static boolean subsetSumMem(int[] ar, int target, int idx, boolean[][] dp) {
    // base case
    if(idx == ar.length || target == 0) {
      boolean achieved = false;
      if(target == 0) {
        achieved = true;
      }
      return achieved;
    }
    if(dp[idx][target] != false)
      return dp[idx][target];
    
    // main logic
    if(ar[idx] <= target) {
      boolean inc = subsetSumMem(ar, target-ar[idx], idx+1, dp);
      boolean exc = subsetSumMem(ar, target, idx+1, dp);
      dp[idx][target] = (inc || exc);
      return dp[idx][target];
    } else {
      dp[idx][target] = subsetSumMem(ar, target, idx+1, dp);
      return dp[idx][target];
    }
  }
  
  
  // Unbounded knapsack Question
  private static void unbounded() {
    int[] val = {15, 14, 10, 45, 30};
    int[] wt = {2, 5, 1, 3, 4};
    int W = 7;
    
    int[][] dp = new int[wt.length+1][W+1];
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) 
        dp[i][j] = -1;
    }
      
    
    System.out.println(tabUnbounded(val, wt, W));
    System.out.println(memUnbounded(val, wt, W, dp, 0));
  }
  private static int memUnbounded(int[] val, int[] wt, int W, int[][] dp, int n) {
    // base case
    if(W == 0 || n == wt.length)
      return 0;
    // main logic
    if(wt[n] <= W) {
      int inc = val[n] + memUnbounded(val, wt, W-wt[n], dp, n);
      int exc = memUnbounded(val, wt, W, dp, n+1);
      dp[n][W] = Math.max(inc, exc);
      return dp[n][W];
    } else {
      dp[n][W] = memUnbounded(val, wt, W, dp, n+1);
      return dp[n][W];
    }
  }
  private static int tabUnbounded(int[] val, int[] wt, int W) {
    int[][] profits = new int[wt.length+1][W+1];
    for(int i=0; i<profits.length; ++i) 
      profits[i][0] = 0;
    for(int j=0; j<profits[0].length; ++j)
      profits[0][j] = 0;
    
    for(int i=1; i<wt.length+1; ++i) {
      for(int j=1; j<W+1; ++j) {
        if(wt[i-1] <= j) {
          int inc = val[i-1] + profits[i][j-wt[i-1]];
          int exc = profits[i-1][j];
          profits[i][j] = Math.max(inc, exc);
        } else {
          profits[i][j] = profits[i-1][j];
        }
      }
    }
    return profits[wt.length][W];
  }
  
  // Coin change question
  static int ways;
  private static void coinChange() {
    int[] coins = {2,5,3,6};
    int sum = 10;
    ways=0;
    
    int[][] dp = new int[coins.length+1][sum+1];
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) 
        dp[i][j] = -1;
    }
    
    //coinChangeUtil(coins, sum, 0, dp);
    System.out.println(ways);
  }
  /*
  private static void coinChangeUtil(int[] coins, int sum, int n, int[][] dp) {
    // base case
    if(n == coins.length || sum <= 0) {
      if(sum == 0)
        ways++;
      return 0;
    }
    // main logic
    if(dp[n][sum] != -1)
      return dp[n][sum];
    if(coins[n] <= sum) {
      dp[n][sum-coins[n]] = coinChangeUtil(coins, sum-coins[n], n, dp);
      dp[n][sum] = coinChangeUtil(coins, sum, n+1, dp);
    } else {
      dp[n][sum] = coinChangeUtil(coins, sum, n+1, dp);
    }
  }
  */
  
  // Rod cutting question
  private static void rodCutting() {
    int[] len = {1,2,3,4,5,6,7,8};
    int[] val = {1,5,8,9,10,17,17,20};
    int rodLen = 8;
    
    int[][] dp = new int[val.length+1][rodLen+1];
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) 
        dp[i][j] = -1;
    }
    
    System.out.println(memRodCutting(len, val, rodLen, 0, dp));
  }
  private static int memRodCutting(int[] len, int[] val, int rodLen, int n, int[][] dp) {
    // base case
    if(n == len.length || rodLen == 0) {
      return 0;
    }
    // main logic
    if(dp[n][rodLen] != -1)
      return dp[n][rodLen];
    
    if(len[n] <= rodLen) {
      int inc = val[n] + memRodCutting(len, val, rodLen-len[n], n+1, dp);
      int exc = memRodCutting(len, val, rodLen, n+1, dp);
      dp[n][rodLen] = Math.max(inc, exc);
      return dp[n][rodLen];
    } else {
      dp[n][rodLen] = memRodCutting(len, val, rodLen, n+1, dp);
      return dp[n][rodLen];
    }
  }
  
  // LCS
  private static void LCS() {
    String str1 = new String("abcdgh");
    String str2 = new String("acdghr");
    
    int[][] dp = new int[str1.length()+1][str2.length()+1];
    for(int i=0; i<dp.length; ++i) {
      for(int j=0; j<dp[0].length; ++j) 
        dp[i][j] = -1;
    }
    
    System.out.println(LCSUtil(str1, str2, str1.length(), str2.length(), dp));
    System.out.println(tabLCS(str1, str2));
  }
  private static int LCSUtil(String str1, String str2, int n, int m, int[][] dp) {
    // base case
    if(n == 0 || m == 0) 
      return 0;
    
    // main logic
    if(dp[n][m] != -1)
      return dp[n][m];
    
    if(str2.charAt(m-1) == str1.charAt(n-1)) {
      dp[n][m] = LCSUtil(str1, str2, n-1, m-1, dp) + 1;
      return dp[n][m];
    } else {
      int ans1 = LCSUtil(str1, str2, n-1, m-1, dp);
      int ans2 = LCSUtil(str1, str2, n, m-1, dp);
      dp[n][m] = Math.max(ans1, ans2);
      return dp[n][m];
    }
  }
  private static int tabLCS(String str1, String str2) {
    int[][] dp = new int[str1.length()+1][str2.length()+1];
    
    for(int i=1; i<str1.length()+1; ++i) {
      for(int j=1; j<str2.length()+1; ++j) {
        if(str1.charAt(i-1) == str2.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1]+1;
        } else {
          dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
        }
      }
    }
    return dp[str1.length()][str2.length()];
  }
  
  
  // longest common substring
  private static void longestSubs() {
    String str1 = "ABCDGH", str2 = "ACDGHR";
    System.out.println(tabLongestSubs(str1, str2, str1.length(), str2.length()));
  }
  private static int tabLongestSubs(String str1, String str2, int n, int m) {
    int[][] dp = new int[str1.length()+1][str2.length()+1];
    int ans = 0;
    
    for(int i=1; i<n+1; ++i) {
      for(int j=1; j<m+1; ++j) {
        if(str2.charAt(i-1) == str1.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1] + 1;
          ans = Math.max(ans, dp[i][j]);
        } else {
          dp[i][j] = 0;
        }
      }
    }
    return ans;
  }
  
  // Longest Increasing Subsequence - LIS
  private static void LIS() {
    int[] arr = {50,3,10,7,40,80};
    System.out.println(lis(arr));
  }
  private static int lis(int[] ar) {
    HashSet<Integer> set = new HashSet<>();
    for(int i=0; i<ar.length; ++i)
      set.add(ar[i]);
    
    int[] ar2 = new int[set.size()];
    int k = 0;
    for(int num : set) {
      ar2[k++] = num;
    }
    
    Arrays.sort(ar2);
    System.out.println("Array sorted");
    //return lcsTAB(ar, ar2);
    
    int[][] dp = new int[ar.length+1][ar2.length+1];
    
    for(int i=0; i<ar.length+1; ++i) {
      for(int j=0; j<ar2.length+1; ++j)
        dp[i][j] = -1;  
    }
      
    return lcsMEM(ar, ar2, ar.length, ar2.length, dp);
  }
  private static int lcsMEM(int[] ar, int[] ar2, int n, int m, int[][] dp) {
    // base case
    if(n == 0 || m == 0)
      return 0;
    
    // main logic
    if(dp[n][m] != -1)
      return dp[n][m];
    if(ar[n-1] == ar2[m-1]) {
      dp[n][m] = lcsMEM(ar, ar2, n-1, m-1, dp) + 1;
      return dp[n][m];
    } else {
      int ans1 = lcsMEM(ar, ar2, n, m-1, dp);
      int ans2 = lcsMEM(ar, ar2, n-1, m, dp);
      dp[n][m] = Math.max(ans1, ans2);
      return dp[n][m];
    }
  }
  private static int lcsTAB(int[] ar, int[] ar2) {
    int n = ar.length, m = ar2.length;
    int[][] dp = new int[n+1][m+1];
    
    for(int i=0; i<n+1; ++i)
      dp[i][0] = 0;
    for(int j=0; j<m+1; ++j)
      dp[0][j] = 0;

    for(int i=1; i<n+1; ++i) {
      for(int j=1; j<m+1; ++j) {
        if(ar[i-1] == ar2[j-1]) {
          dp[i][j] = dp[i-1][j-1]+1;
        } else {
          int ans1 = dp[i-1][j];
          int ans2 = dp[i][j-1];
          dp[i][j] = Math.max(ans1, ans2);
        }
      }
    }
    return dp[n][m];
  }
  
  // Edit Distance Question 
  private static void editDist() {
    String str1 = "pear", str2 = "sea";
    int n = str1.length(), m = str2.length();
    
    int[][] dp = new int[n+1][m+1];
    for(int i=0; i<n+1; ++i) 
      dp[i][0] = i;
    for(int j=0; j<m+1; ++j)
      dp[0][j] = j;
    
    //System.out.println(editDistMEM(str1, str2, n, m, dp));
    System.out.println(editDistTAB(str1, str2));
  }
    
  private static int editDistMEM(String str1, String str2, int n, int m, int[][] dp) {
    // base case
    if(n == 0 || m == 0)
      return dp[n][m];
    // main logic
    
    if(dp[n][m] != 0)
      return dp[n][m];
      
    if(str1.charAt(n-1) == str2.charAt(m-1)) {
      dp[n][m] = editDistMEM(str1, str2, n-1, m-1, dp);
      return dp[n][m];
    } else {
      int add = editDistMEM(str1, str2, n, m-1, dp)+1;
      int del = editDistMEM(str1, str2, n-1, m, dp)+1;
      int rep = editDistMEM(str1, str2, n-1, m-1, dp)+1;
      dp[n][m] = Math.min(add, Math.min(del, rep));
      return dp[n][m];
    }
  }
  private static int editDistTAB(String str1, String str2) {
    int n = str1.length(), m = str2.length();
    int[][] dp = new int[n+1][m+1];
    for(int i=0; i<n+1; ++i) 
      dp[i][0] = i;
    for(int j=0; j<m+1; ++j) 
      dp[0][j] = j;
    
    for(int i=1; i<n+1; ++i) {
      for(int j=1; j<m+1; ++j) {
        if(str1.charAt(i-1) == str2.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1];
        } else {
          int add = dp[i][j-1]+1;
          int del = dp[i-1][j]+1;
          //int rep = dp[i-1][j-1]+1;
          dp[i][j] = Math.min(add, del);//Math.min(del, rep));
        }
      }
    }
    return dp[n][m];
  }
  
   
  
  
  public static void main(String[] args) {
    editDist();
  }
}
  

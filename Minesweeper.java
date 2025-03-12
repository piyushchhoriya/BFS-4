
## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

As in this there are connected componentes i.e if we click one cell then it is affecting other cells so we can say that there are connected componenents
Now we can solve this question using DFS or BFS

//BFS
In this what we are doing is as we want to explore the adjacent 8 elements so we will keep directions array and  m,n in global scope.
Also as the problem states that if the cell is a Mine and we reveal it the game is over. Here the understanding is we will only reveal the
Mine if it is at the given click position else we will never reveal it because as we go we are exploring 8 adjacent elements and if any 
of those are Mine we are marking it from 1-8 and not exploring it
In this as we are exploring for loop based recursion we are keeping the index track so we will not need the base containsKey

Time Complexity : O(m*n)
Space Complexity : O(m*n)
//My solution :
class Solution {
    //Global Declaration 
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        //base case check
        if(board.length==0 || board==null){
            return board;
        }
        //This will only happen if the click index are M
        if(board[click[0]][click[1]]== 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        //dirs array initialisation
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        m = board.length;
        n = board[0].length;
        //doing this for the given click index
        int len = calculate(board,click);
        if(len>0){
           board[click[0]][click[1]] = (char)(len+'0'); 
        }
        dfs(board,click);
        return board;
    }   

    private void dfs(char[][] board, int[] click){
        //base
        
        //logic
        if(board[click[0]][click[1]]=='E'){
            board[click[0]][click[1]] = 'B';
            for(int i=0;i<dirs.length;i++){
                int nr = click[0] + dirs[i][0];
                int nc = click[1] + dirs[i][1];
                if(nr < m && nr >= 0 && nc < n && nc >=0 && board[nr][nc] == 'E'){
                    int len = calculate(board,new int[]{nr,nc});
                    if(len > 0){
                        board[nr][nc] = (char)(len+'0');
                    }
                    else{
                        dfs(board,new int[]{nr,nc});
                    }

                }
            }
        }
        else{

        }
    }
    //This function will go and see the adjacent 8 elements and if there is M it will increment the total and return it
    private int calculate(char[][] board, int[] click){
        int total=0;
        for(int i=0;i<dirs.length;i++){
            int nr = click[0] + dirs[i][0];
            int nc = click[1] + dirs[i][1];
            if(nr < m && nr >= 0 && nc < n && nc >=0 && board[nr][nc] == 'M'){
                total++;
            }
        }
        return total;
    }
}

//Code optimized solution given by sir
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length==0 || board==null){
            return board;
        }
        if(board[click[0]][click[1]]== 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        m = board.length;
        n = board[0].length;
        dfs(board,click);
        return board;
    }   
    private void dfs(char[][] board, int[] click){
        //base
        if(click[0] == m || click[0] < 0 || click[1] == n || click[1] < 0 ||  board[click[0]][click[1]] != 'E'){
            return;
        }

        //logic
        int len = calculate(board,click);
        if(len==0){
            board[click[0]][click[1]] = 'B';
            for(int i=0;i<dirs.length;i++){
                int nr = click[0] + dirs[i][0];
                int nc = click[1] + dirs[i][1];
                dfs(board,new int[]{nr,nc}); 
            }

        }
        else{
            board[click[0]][click[1]] = (char)(len+'0');
        }
    }
    private int calculate(char[][] board, int[] click){
        int total=0;
        for(int i=0;i<dirs.length;i++){
            int nr = click[0] + dirs[i][0];
            int nc = click[1] + dirs[i][1];
            if(nr < m && nr >= 0 && nc < n && nc >=0 && board[nr][nc] == 'M'){
                total++;
            }
        }
        return total;
    }
}

//Using BFS
Time Complexity : O(m*n)
Space Complexity : O(m*n)
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length==0 || board==null){
            return board;
        }
        if(board[click[0]][click[1]]== 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        m = board.length;
        n = board[0].length;
        Queue<int[]> q=new LinkedList<>();
        q.add(click);
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int len = calculate(board,curr);
            if(len==0){
                board[curr[0]][curr[1]] = 'B';
                for(int i=0;i<dirs.length;i++){
                    int nr = curr[0] + dirs[i][0];
                    int nc = curr[1] + dirs[i][1];
                    if(nr < m && nr >= 0 && nc < n && nc >=0 && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(len+'0');
            }
        }
        
        return board;
    }   
    private int calculate(char[][] board, int[] click){
        int total=0;
        for(int i=0;i<dirs.length;i++){
            int nr = click[0] + dirs[i][0];
            int nc = click[1] + dirs[i][1];
            if(nr < m && nr >= 0 && nc < n && nc >=0 && board[nr][nc] == 'M'){
                total++;
            }
        }
        return total;
    }
}
## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)
This is a snakes and ladders question in which either the cell is -1 or a +ve value which either depicts a snake or a ladder.
As the given matrix is in alternate rows order we will first flatten it into a 1-D array.
//Time Complexity : O(n^2)
//Space Complexity : O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        //base case
       if(board==null || board.length == 0){
            return 0;
       }
       //Queue for BFS traversal
       Queue<Integer> q = new LinkedList<>();
       int moves=0;
       int n = board.length;
       //nums array for flattening the given matrix
       int[] nums = new int[n*n];
       int index = 0;
       int row=n-1;
       int col=0;
       int even = 0;
       //We are transforming the given matrix into 1-D array
       while(index < n*n){
        if(board[row][col]!=-1){
            nums[index] = board[row][col]-1;
        }
        else{
            nums[index]=-1;
        }
        index++;
        if(even%2==0){
            col++;
            if(col==n){
                row--;
                even++;
                col=n-1;
            }
        }
        else{
            col--;
            if(col==-1){
                row--;
                even++;
                col=0;
            }
        }
       }
       //we are adding the first element into the queue
       q.add(0); 
       //BFS traversal
        while(!q.isEmpty()){
            //we need a size variable to keep track of no of moves
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr = q.poll();
                if(curr==n*n-1){
                    return moves;
                }
                //every roll of the dice wioll have six choices
                for(int j=1;j<=6;j++){
                    //calculating current polled and the choices
                    int child=curr+j;
                    // if the child is >= n*n then it is out of bound so continue
                    if(child >= n*n){
                        continue;
                    }
                    //we are marking the visited cells as -2 and we will not process them
                    if(nums[child]!=-2){
                        //if it is -1 then we will add the child in queue
                        if(nums[child]==-1){
                            q.add(child);
                            nums[child]=-2;
                        }
                        //else we will add the value at that index
                        else{
                            q.add(nums[child]);
                            nums[child]=-2;
                        }
                    }
                }
            }
            //we will increment moves here
            moves++;
        }
        // if we cant reach then we will return -1
        return -1;
    }
}
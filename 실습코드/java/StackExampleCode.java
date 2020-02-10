import java.util.Stack;

public class test1 {
	
	
	public static void main(String args[]) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		solution(board, moves);
		
	}
	    public static int solution(int[][] board, int[] moves) {
	        int answer = 0;
	        
	        Stack<Integer> basket = new Stack<Integer>();
	        
	        
	        for(int i=0; i<moves.length; i++) {
	        	int column = moves[i]-1; 
	        	for(int j=0; j<board.length; j++) {
	        		if(board[j][column] != 0) { 
	        			if((basket.size() != 0) && (board[j][column] == basket.peek())){
	        				answer += 2;
	        				basket.pop();
	        			}else {
	        				basket.add(board[j][column]);
	        			}
	        			board[j][column] = 0; // move completed
	        			break;
	        		}
	        		
	        	}
	        }
	          
	        return answer;
	        
	    }
	}


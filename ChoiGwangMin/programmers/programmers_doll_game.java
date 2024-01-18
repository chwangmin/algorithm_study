class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;    
        int boardSize= board[0].length; // 5
        
        int []boardBlockArr = new int[900];
        
        for (int move : moves){
            int tmpBoardBlock = 0;
            for (int i = 0; i < boardSize; i++){
                int currentBoardBlock = board[i][move-1];
                if (currentBoardBlock != 0){
                    tmpBoardBlock = currentBoardBlock;
                    board[i][move-1] = 0;
                    break;
                }
            }
            if (tmpBoardBlock != 0){
                for (int i = 0; i < 900; i++){
                    if (boardBlockArr[i] == 0){
                        boardBlockArr[i] = tmpBoardBlock;
                        if (i > 0){
                            if (boardBlockArr[i] == boardBlockArr[i-1]){
                                boardBlockArr[i] = 0;
                                boardBlockArr[i-1] = 0;
                                answer += 2;
                            }
                        }
                        break;
                    }
                }
            }
        }
        
        System.out.println(answer);
        
        return answer;
    }
}

/** 
1. moves를 한바퀴 돈다.
2. 첫번째 값을 가져온뒤 list를 세로로 찾아서 가장 빨리 찾은 값을 저장하고 뺀다.
3. 그 찾은 값을 저장 리스트에 입력한다.
4. 만약 저장 리스트의 값의 주변 값과 같다면 answer + 2를 한다.
5. 그 값 두개를 모두 삭제한고 다시 리스트 검사를 한다. (4 다시)

만약 board가 없어지면서 가운데에 연속된 수가 되는 경우는 없음.
**/

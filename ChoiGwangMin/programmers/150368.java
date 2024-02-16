import java.util.Arrays;

class Solution {
    static int[] emoticonsS, emoticonsSails, tmpS, answerS;
    static int[][] usersS;
    
    public int[] solution(int[][] users, int[] emoticons) {
        tmpS = new int[2];
        answerS = new int[2];
        usersS = users;
        emoticonsS = emoticons;
        
        emoticonsSails = new int[emoticons.length];
        
        findPrice(0);
        
        return answerS;
    }
    
    static void findPrice(int idx){
        if (idx == emoticonsS.length){
            checkAll();
            if (tmpS[0] < answerS[0]){
                return;
            }
            if (tmpS[0] > answerS[0]){
                answerS = Arrays.copyOf(tmpS,2);
                return;
            }
            if (tmpS[1] > answerS[1]){
                answerS = Arrays.copyOf(tmpS,2);
            }
            return;
        }
        
        for (int i = 10 ; i <= 40; i+=10){
            emoticonsSails[idx] = i;
            findPrice(idx+1);
        }
    }
    
    static void checkAll(){
        tmpS[0] = 0; tmpS[1] = 0;
        for (int u = 0; u < usersS.length; u++){
            int price = 0;
            for (int i = 0; i < emoticonsS.length; i++){
                if (usersS[u][0] > emoticonsSails[i]){
                    continue;
                }
                price += emoticonsS[i] - (emoticonsS[i] / 100 * emoticonsSails[i]);
            }
            if (usersS[u][1] <= price){
                tmpS[0]++;
                continue;
            }
            tmpS[1] += price;
        }
    }
}
// 최악의 시간복잡도는 4 * 7 * 100

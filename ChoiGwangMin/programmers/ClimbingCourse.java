import java.util.*;
import java.awt.*;

class Solution {
    class Node{
        ArrayList<EndWeight> endNode;
        Node(){
            endNode = new ArrayList<>();
        }
    }

    class EndWeight {
        int e, w;
        EndWeight(int e, int w){
            this.e = e;
            this.w = w;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        answer[1] = 1_234_567_890;
        Node[] nodes = new Node[n+1];

        for (int i = 1 ; i < n+1; i++){
            nodes[i] = new Node();
        }

        for (int i = 0 ; i < paths.length; i++){
            nodes[paths[i][0]].endNode.add(new EndWeight(paths[i][1],paths[i][2]));
            nodes[paths[i][1]].endNode.add(new EndWeight(paths[i][0],paths[i][2]));
        }

        for(int i = 0 ; i < gates.length; i++){
            int[] visited = new int[n+1];
            Arrays.fill(visited, Integer.MAX_VALUE);
            Deque<EndWeight> q = new ArrayDeque<>();
            q.add(new EndWeight(gates[i],0));
            visited[gates[i]] = 0;
            boolean flag = false;
            while(!q.isEmpty()){
                EndWeight ew = q.poll();
                int current = ew.e;
                for (int k = 0 ; k < nodes[current].endNode.size(); k++){
                    int tmpCurrentNode = nodes[current].endNode.get(k).e;
                    int tmpCurrentWeight = nodes[current].endNode.get(k).w;
                    int currentMaxWeight = Math.max(ew.w, tmpCurrentWeight);
                    if(visited[tmpCurrentNode] <= currentMaxWeight) continue;
                    if(currentMaxWeight > answer[1]) continue;
                    for (int j = 0 ; j < summits.length; j++){
                        if (summits[j] == tmpCurrentNode){
                            if (answer[1] > currentMaxWeight){
                                answer[0] = tmpCurrentNode;
                            }
                            else if (answer[1] == currentMaxWeight){
                                answer[0] = Math.min(answer[0],tmpCurrentNode);
                            }
                            answer[1] = currentMaxWeight;
                            flag = true;
                            break;
                        }
                    }
                    visited[tmpCurrentNode] = currentMaxWeight;
                    if (flag){
                        flag = false;
                        continue;
                    }
                    q.add(new EndWeight(tmpCurrentNode,currentMaxWeight));
                }
            }
        }
        return answer;
    }
}

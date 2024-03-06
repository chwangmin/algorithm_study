import java.util.*;
import java.awt.*;

class Solution {
    class Node{
        ArrayList<EndWeight> endNode;
        Node(){
            endNode = new ArrayList<>();
        }
    }
    
    class EndWeight implements Comparable<EndWeight>{
        int e, w;
        EndWeight(int e, int w){
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(EndWeight o){
            return Integer.compare(o.w, this.w);
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
            PriorityQueue<EndWeight> pq = new PriorityQueue<>();
            pq.add(new EndWeight(gates[i],0));
            visited[gates[i]] = 0;
            boolean flag = false;
            while(!pq.isEmpty()){
                EndWeight ew = pq.poll();
                int current = ew.e;
                for (int k = 0 ; k < nodes[current].endNode.size(); k++){
                    int tmpCurrentNode = nodes[current].endNode.get(k).e;
                    int tmpCurrentWeight = nodes[current].endNode.get(k).w;
                    int tmpWeight = Math.max(ew.w, tmpCurrentWeight);
                    if(visited[tmpCurrentNode] <= tmpWeight)
                        continue;
                    if(tmpCurrentWeight > answer[1])
                        continue;
                    for (int j = 0 ; j < summits.length; j++){
                        if (summits[j] == tmpCurrentNode){
                            int currentMax = Math.max(ew.w,tmpCurrentWeight);
                            if (answer[1] > currentMax){
                                answer[0] = tmpCurrentNode;
                                answer[1] = currentMax;
                            }
                            else if (answer[1] == currentMax){
                                answer[0] = Math.min(answer[0],tmpCurrentNode);
                            }
                            flag = true;
                            break;
                        }
                    }
                    if (flag){
                        flag = false;
                        continue;
                    }
                    visited[tmpCurrentNode] = tmpWeight;
                    pq.add(new EndWeight(tmpCurrentNode,tmpWeight));
                }
            }
        }
        return answer;
    }
}

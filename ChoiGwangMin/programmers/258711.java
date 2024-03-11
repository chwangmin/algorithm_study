import java.util.*;

class Solution {
    static class Node {
        int inNodeNum;
        ArrayList<Integer> outNodes;
        
        private Node(){
            inNodeNum = -1;
            outNodes = new ArrayList<>();
        }
    }
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        Node[] nodes = new Node[1_000_001];
        
        for (int i = 1 ; i < 1_000_001; i++){
            nodes[i] = new Node();
        }
        
        for (int i = 0 ; i < edges.length; i++){
            if (nodes[edges[i][0]].inNodeNum == -1){
                nodes[edges[i][0]].inNodeNum = 0;
            }
            if (nodes[edges[i][1]].inNodeNum == -1){
                nodes[edges[i][1]].inNodeNum = 0;
            }
            nodes[edges[i][0]].outNodes.add(edges[i][1]);
            nodes[edges[i][1]].inNodeNum++;
        }
        
        int startNode = 0;
        
        for(int i = 1; i < 1_000_001; i++){
            if (nodes[i].inNodeNum == 0 && nodes[i].outNodes.size() > 1){
                startNode = i;
                break;
            }
        }
        
        answer[0] = startNode;
        
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[1_000_001];
        
        for (int i = 0 ; i < nodes[startNode].outNodes.size(); i++){
            int startGetNode = nodes[startNode].outNodes.get(i);
            
            if(nodes[startGetNode].inNodeNum == 1 || nodes[startGetNode].outNodes.size() == 0){
                answer[2]++;
                continue;
            }
            else if (nodes[startGetNode].inNodeNum == 3){
                answer[3]++;
                continue;
            }
            
            q.add(startGetNode);
            visited[startGetNode] = true;
            
            int flag = 0;
            
            while(!q.isEmpty()){
                int currentNode = q.poll();
            
                for (int j = 0; j < nodes[currentNode].outNodes.size(); j++){
                    
                    int currentGetNode = nodes[currentNode].outNodes.get(j);
                    
                    if (visited[currentGetNode]){
                        continue;
                    }
                    
                    if (nodes[currentGetNode].inNodeNum == 2){
                        flag = 3;
                        break;
                    }
                    
                    if (nodes[currentGetNode].outNodes.size() == 0){
                        flag = 2;
                        break;
                    }
                    
                    visited[currentGetNode] = true;
                    q.add(currentGetNode);
                }
                if (flag != 0){
                    break;
                }
            }
            
            q.clear();
            
            if (flag == 3){
                answer[3]++;
                continue;
            }
            else if (flag == 2){
                answer[2]++;
                continue;
            }
            answer[1]++;
        }
        
        return answer;
    }
}

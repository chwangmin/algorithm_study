import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ACM1005 {
    static class Node {
        ArrayList<Integer> inputNode;
        ArrayList<Integer> outputNode;

        Node(){
            inputNode = new ArrayList<>();
            outputNode = new ArrayList<>();
        }
    }

    static class NV implements Comparable<NV>{
        int node;
        int value;

        @Override
        public int compareTo(NV o) {
            return Integer.compare(this.value, o.value);
        }

        NV(int node, int value){
            this.node = node;
            this.value = value;
        }
    }

    static int[] list;
    static Node[] nodes;

    static int W;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= null;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int testNum = 0 ; testNum < testCase; testNum++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            list = new int[N+1];
            nodes = new Node[N+1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1 ; i < N + 1; i++){
                list[i] = Integer.parseInt(st.nextToken());
                nodes[i] = new Node();
            }

            for (int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                nodes[start].outputNode.add(end);
                nodes[end].inputNode.add(start);
            }
            W = Integer.parseInt(br.readLine());

            sb.append(checkW()).append("\n");
        }
        System.out.println(sb);
    }

    static int checkW(){
        PriorityQueue<NV> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];

        ArrayList<NV> tmpNode = new ArrayList<>();

        for (int i = 1 ; i < N+1; i++){
            if (nodes[i].inputNode.size() == 0){
                pq.add(new NV(i,list[i]));
                visited[i] = true;
            }
        }

        int answer = 0;

        while(!pq.isEmpty()){
            NV tmpNV = pq.poll();

            int node = tmpNV.node;
            int value = tmpNV.value;

            answer += value;

            if (node == W){
                break;
            }

            while(!pq.isEmpty()){
                tmpNode.add(pq.poll());
            }

            for (int i = 0; i < tmpNode.size(); i++){
                tmpNode.get(i).value -= value;
                pq.add(tmpNode.get(i));
            }

            tmpNode.clear();

            for (int i = 0 ; i < nodes[node].outputNode.size(); i++){
                int outputNode = nodes[node].outputNode.get(i);
                for (int j = 0; j < nodes[outputNode].inputNode.size(); j++) {
                    if (nodes[outputNode].inputNode.get(j) == node) {
                        nodes[outputNode].inputNode.remove(j);
                    }
                }
                if (nodes[outputNode].inputNode.size() == 0){
                    if (visited[outputNode]){
                        continue;
                    }
                    visited[outputNode] = true;
                    pq.add(new NV(outputNode, list[outputNode]));
                }
            }
        }

        return answer;
    }
}

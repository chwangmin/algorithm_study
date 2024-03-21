import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, ArrayList<String>> folderMap = new HashMap<>();
    static HashMap<String, HashSet<String>> fileMap = new HashMap<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < N+M; i++){
            st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            int C = Integer.parseInt(st.nextToken());

            if (C == 0){
                if (!fileMap.containsKey(A)) {
                    fileMap.put(A, new HashSet<>());
                }
                fileMap.get(A).add(B);
            } else {
                if (!folderMap.containsKey(A)){
                    folderMap.put(A, new ArrayList<>());
                }
                folderMap.get(A).add(B);
            }
        }

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < Q; i++){
            String[] tmpStr = br.readLine().split("/");
            ansSet = new HashSet<>();
            ansCnt = 0;

            String key = tmpStr[tmpStr.length-1];

            dfs(key);

            sb.append(ansSet.size()).append(" ").append(ansCnt).append("\n");
        }

        System.out.println(sb);
    }

    static HashSet<String> ansSet;
    static int ansCnt;

    static void dfs(String key){
        if (folderMap.containsKey(key)){
            for (int i = 0 ; i < folderMap.get(key).size(); i++) {
                dfs(folderMap.get(key).get(i));
            }
        }

        if (fileMap.containsKey(key)){
            ansSet.addAll(fileMap.get(key));
            ansCnt += fileMap.get(key).size();
        }
    }
}

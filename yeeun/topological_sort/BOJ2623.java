package codingstudy202107.w24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ2623 {
    public static void main(String[] args) throws IOException {
        BOJ2623 main = new BOJ2623();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int singerCount = Integer.parseInt(st.nextToken());
        int pdCount = Integer.parseInt(st.nextToken());

        Singer[] arr = new Singer[singerCount + 1];
        for (int i = 1; i <= singerCount; i++) arr[i] = new Singer(i);

        for (int i = 0; i < pdCount; i++) {
            st = new StringTokenizer(reader.readLine());
            Singer prev = null;
            int singerAmount = Integer.parseInt(st.nextToken());
            for (int j=0;j<singerAmount;j++) {
                int singerIdx = Integer.parseInt(st.nextToken());
                Singer singer = arr[singerIdx];
                if (prev != null) {
                    prev.nextSingers.add(singer);
                    singer.prevSingerCount += 1;
                }
                prev = singer;
            }
        }

        Queue<Singer> queue = new ArrayDeque<>();
        if(main.fillQueueWithOrder(arr, queue)){
            System.out.print(0);
        }else{
            StringBuilder builder = new StringBuilder();
            while (!queue.isEmpty()) {
                builder.append(queue.poll().val);
                builder.append('\n');
            }
            builder.setLength(builder.length() - 1);
            System.out.print(builder);
        }
    }

    private boolean fillQueueWithOrder(Singer[] arr, Queue<Singer> queue) {
        Vector<Singer> noPrevSingers = new Vector<>();
        int remain = this.addNoPrevSingersToQueue(arr, noPrevSingers);
        if (remain > 0 && noPrevSingers.isEmpty()) return true;
        while (!noPrevSingers.isEmpty()) {
            Singer singer = noPrevSingers.remove(0);
            queue.add(singer);
            this.subtractSingersAfterNoPrevSingers(singer);
            remain = this.addNoPrevSingersToQueue(arr, noPrevSingers);
            if (remain > 0 && noPrevSingers.isEmpty()) return true;
        }
        return false;
    }

    private void subtractSingersAfterNoPrevSingers(Singer singer) {
        for (Singer next : singer.nextSingers) {
            next.prevSingerCount -= 1;
        }
    }

    private int addNoPrevSingersToQueue(Singer[] arr, Vector<Singer> noPrevSingers) {
        int result = 0;
        for (Singer sin : arr) {
            if (sin != null && sin.prevSingerCount == 0 && !sin.added) {
                noPrevSingers.add(sin);
                sin.added = true;
            } else if (sin != null && sin.prevSingerCount > 0 && !sin.added) {
                result++;
            }
        }
        return result;
    }


    public static class Singer {
        int val;
        boolean added = false;
        Vector<Singer> nextSingers = new Vector<>(1);
        int prevSingerCount = 0;

        public Singer(int val) {
            this.val = val;
        }
    }
}

package com.zentra.zentra.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FuzzySearch {


    private static void swap(List<HashMap<NameUUID, Integer>> list, int i, int j ) {
        HashMap<NameUUID, Integer> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    private static int onlyValue (HashMap<NameUUID, Integer> map){
        return map.values().iterator().next();
    }
    private static int partition(List<HashMap<NameUUID, Integer>> list, int low, int high) {
        int pivot = onlyValue(list.get(high));

        int i = low - 1;

        for(int j = low; j <= high - 1; j++) {
            int current = onlyValue(list.get(j));
            if (current <= pivot) {
                i++;
                swap(list, i, j);
            }

        }
        swap(list, i + 1, high);
        return i + 1;

    }

    private static void quickSort(List<HashMap<NameUUID, Integer>> list, int low, int high){
        if(low < high){
            int pi = partition(list,low,high);
            quickSort(list,low,pi-1);
            quickSort(list,pi+1,high);
        }
    }

    private static int levenshteinDistance(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[n][m];
    }
    public static List<NameUUID> fuzzySearch (String query, List<NameUUID> nameUUIDS) {
        List<HashMap<NameUUID, Integer >> maps = new ArrayList<>();

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            List<Future<HashMap<NameUUID, Integer>>> futures = new ArrayList<>(nameUUIDS.size());
            for(var nameUUID : nameUUIDS){
                futures.add(executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                    int distance = levenshteinDistance(query, nameUUID.getName());
                    HashMap<NameUUID, Integer> map = new HashMap<>();
                    map.put(nameUUID, distance);
                    return map;
                }));

            }
            for (var f: futures) {
                maps.add(f.get());
            }
        } catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (maps.isEmpty()) return List.of();
        quickSort(maps, 0, maps.size() - 1);
        return maps.stream()
                .map(s->s.keySet().iterator().next())
                .toList();
    }

}

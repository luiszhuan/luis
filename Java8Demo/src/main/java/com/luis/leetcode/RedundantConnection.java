package com.luis.leetcode;

//https://leetcode-cn.com/problems/redundant-connection/
public class RedundantConnection {
    public static void main(String[] args) {
        //[[1,2], [1,3], [2,3]]
        //int[][] edges=new int[][]{{1,2}, {1,3}, {2,3}};
        int[][] edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] result = new RedundantConnection().findRedundantConnection(edges);
        System.out.println(result);
    }

    public int[] findRedundantConnection(int[][] edges) {
        int[] degrees = new int[1001];
        for (int i = 0; i < edges.length; i++) {
            degrees[edges[i][0]] += 1;
            degrees[edges[i][1]] += 1;
        }

        int node = 0;
        do {
            node = 0;
            for (int i = 1; i < 1001; i++) {
                if (degrees[i] == 1) {
                    node = i;
                    break;
                }
            }

            for (int i = 0; i < edges.length && node != 0; i++) {
                if (edges[i][0] == node || edges[i][1] == node) {
                    degrees[edges[i][0]] -= 1;
                    degrees[edges[i][1]] -= 1;
                    edges[i][0] = 0;
                    edges[i][1] = 0;
                }
            }
        } while (node != 0);
        for (int i = edges.length - 1; i >= 0; i--) {
            if (edges[i][0] != 0 && edges[i][1] != 0) {
                return edges[i];
            }
        }
        return null;
    }
}

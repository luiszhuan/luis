package com.luis.leetcode;

import java.util.*;

//https://leetcode-cn.com/problems/accounts-merge/solution/zhang-hu-he-bing-by-leetcode/
public class MergeAccounts {

    public static void main(String[] args) {

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emails2Name = new HashMap<>();
        Map<String, Integer> emails2Id = new HashMap<>();
        int id = 0;
        DSU dsu = new DSU();
        for (List<String> account : accounts) {
            String name = null;
            for (String email : account) {
                //first string is the name of account not email
                if (name == null) {
                    name = email;
                    continue;
                }
                emails2Name.put(email, name);
                if (!emails2Id.containsKey(email)) {
                    emails2Id.put(email, id++);
                }
                dsu.union(emails2Id.get(account.get(1)), emails2Id.get(email));
            }
        }


        // List<List<String>> ans = new ArrayList<>();
        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email : emails2Name.keySet()) {
            int parent = dsu.find(emails2Id.get(email));
            ans.computeIfAbsent(parent, x -> new ArrayList<>()).add(email);
        }
        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emails2Name.get(component.get(0)));
        }

        return new ArrayList<>(ans.values());
    }

    class DSU {
        private int parent[];

        public DSU() {
            parent = new int[1000 * 10];
            for (int i = 0; i < 1000 * 10; i++) {
                parent[i] = i;
            }
        }

        //find top parent, and set accordingly.
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        //set parent of x to parent of y
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}


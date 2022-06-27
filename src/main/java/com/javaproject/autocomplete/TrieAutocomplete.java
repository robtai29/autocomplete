package com.javaproject.autocomplete;

import java.io.FileNotFoundException;
import java.util.*;
import setup.FileManager;
public  class TrieAutocomplete extends Node  {
    private  Node root;

    private static HashMap<String, String> casingMap=new HashMap<String,String>();


    TrieAutocomplete(String filePath) throws FileNotFoundException {
        this.root =  new Node('\0');
        FileManager.setFile(filePath);
        FileManager.scanFile(filePath,this);
    }

    public void setNodes (String[] terms, double[] weights) {

        if (terms == null || weights == null) {
            throw new NullPointerException();
        }
        //dummy root
        //root = new Node('\0');

        for (int i = 0; i < terms.length; i++) {
            add(terms[i], weights[i]);
        }
    }

    private void add(String word, double weight) {
        if (word == null) {
            throw new NullPointerException();
        }

        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.getLeaves().containsKey(ch)){
                current.getLeaves().put(ch, new Node(ch));
            }
            current = current.getLeaves().get(ch);
        }

        current.setWord(word);
        current.setWeight(weight);
    }


    public String[] topKMatches(String prefix, int k) {
        if (prefix == null){
            throw new NullPointerException();
        }

        if (k == 0){
            throw new IllegalArgumentException();
        }

        //get the node at prefix
        Node current = root;
        for (char ch : prefix.toCharArray()){
            if (!current.getLeaves().containsKey(ch)){
                return new String[]{};
            }
            current = current.getLeaves().get(ch);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.getWeight() > 0){
                pq.add(node);
            }

                for (char child : node.getLeaves().keySet()) {
                    queue.add(node.getLeaves().get(child));
                }
        }

        while (pq.size() > k){
            pq.poll();
        }

        //return string array in descending order of weight
        String[] res = new String[Math.min(k, pq.size())];
        for (int i = res.length-1; i >=0; i--){
            res[i] = pq.poll().getWord();
        }
        return res;
    }

    public void setCasingMap(HashMap<String, String> casingMap) {
        this.casingMap = casingMap;
    }

    public String getCasing(String word){

        return casingMap.get(word);

    }
}

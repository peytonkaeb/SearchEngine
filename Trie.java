//IMPORTANT NOTE: I did not write this, I got it from https://www.geeksforgeeks.org/dsa/auto-complete-feature-using-trie/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Trie class: implements a trie (prefix tree) for fast word lookup and suggestions
public class Trie {

    // Inner class representing each node in the trie
    public class TrieNode {
        Map<Character, TrieNode> children; // stores children nodes mapped by character
        char c;                             // character stored in this node
        boolean isWord;                     // true if this node marks the end of a word

        // Constructor for a node with a specific character
        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();    // initialize children map
        }

        // Default constructor for the root node (no character)
        public TrieNode() {
            children = new HashMap<>();
        }

        // Insert a word starting from this node
        public void insert(String word) {
            if (word == null || word.isEmpty()) // base case: empty string, do nothing
                return;

            char firstChar = word.charAt(0);     // take the first character of the word
            TrieNode child = children.get(firstChar); // check if child node exists

            if (child == null) {                 // if no child for this char exists
                child = new TrieNode(firstChar); // create a new TrieNode
                children.put(firstChar, child);  // add it to children map
            }

            if (word.length() > 1)               // if more characters remain
                child.insert(word.substring(1)); // recursively insert the rest of the word
            else
                child.isWord = true;             // mark this node as end of a word
        }

    }

    TrieNode root; // root of the trie

    // Trie constructor: takes a list of words and inserts them into the trie
    public Trie(List<String> words) {
        root = new TrieNode();                  // create root node
        for (String word : words)
            root.insert(word);                  // insert each word into the trie
    }

    // Find method: checks if a prefix exists in the trie
    public boolean find(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {     // traverse each character in the prefix
            lastNode = lastNode.children.get(c);  // move to the corresponding child
            if (lastNode == null)                 // if any character not found
                return false;                     // prefix does not exist
        }
        return !exact || lastNode.isWord;        // if exact match required, check isWord
    }

    // Overloaded find method: default search for any prefix (not necessarily full word)
    public boolean find(String prefix) {
        return find(prefix, false);
    }

    // Recursive helper for autocomplete suggestions
    public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isWord) {                    // if this node is end of a word
            list.add(curr.toString());        // add current word to suggestions list
        }

        if (root.children == null || root.children.isEmpty()) // no more children
            return;

        // recursively visit all children
        for (TrieNode child : root.children.values()) {
            curr.append(child.c);                      // add child char to current word
            suggestHelper(child, list, curr);         // recursive call
            curr.setLength(curr.length() - 1);        // backtrack: remove last char
        }
    }

    // Suggest words based on a prefix
    public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();  // list to store suggestions
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer(); // buffer for building current word

        for (char c : prefix.toCharArray()) {   // traverse the prefix
            lastNode = lastNode.children.get(c); 
            if (lastNode == null)               // prefix not found
                return list;                    // return empty list
            curr.append(c);                     // add prefix char to buffer
        }

        // recursively find all words starting from lastNode
        suggestHelper(lastNode, list, curr);
        return list;
    }





}

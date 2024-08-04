package practice25;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        sc.nextLine();
        Trie trie = new Trie();
        for(int i = 0; i< m; i++) {
            String command = sc.next();
            String argument = sc.nextLine().trim();
            switch(command) {
                case "ADD":
                    trie.insert(argument);
                    break;
                case "REMOVE":
                    trie.delete(argument);
                    break;
                case "SEARCH":
                    List<String> words = trie.searchByPrefix(argument);
                    if(words.isEmpty()) {
                        System.out.println("404 Not found");
                    } else {
                        words.sort(null);
                        for(String word: words) {
                            System.out.println(word);
                        }
                    }
                    System.out.println("---");
                    break;

            }
        }
    }
}

class Trie {
    class TrieNode {
        Map<Character, TrieNode> child;
        boolean isEnd;

        public TrieNode() {
            child = new HashMap<>();
            isEnd = false;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.child.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEnd = true;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    boolean delete(TrieNode current, String word, int index) {
        // base case
        // 문자열의 끝에 도달하는 경우
        if (index == word.length()) {
            if (!current.isEnd) {
                return false;
            }
            current.isEnd = false;
            return current.child.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode node = current.child.get(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDelete = delete(node, word, index + 1);
        if (shouldDelete && !node.isEnd) {
            current.child.remove(ch);
            return current.child.isEmpty();
        }
        return false;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.child.get(ch);
            if (current == null) {
                return false;
            }
        }
        return current.isEnd;
    }

    public List<String> searchByPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.child.get(ch);
            if (current == null) {
                return Collections.emptyList();
            }
        }
        List<String> results = new ArrayList<>();
        findAllWord(current, new StringBuilder(prefix), results);
        return results;
    }

    void findAllWord(TrieNode node, StringBuilder prefix, List<String> result) {
        if (node.isEnd) {
            result.add(prefix.toString());
        }
        for (char ch : node.child.keySet()) {
            prefix.append(ch);
            findAllWord(node.child.get(ch), prefix, result);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
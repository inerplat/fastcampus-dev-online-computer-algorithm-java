package practice25;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Trie trie = new Trie();

        for (int i = 0; i < m; i++) {
            String command = scanner.next();
            String argument = scanner.nextLine().trim();
            switch (command) {
                case "ADD":
                    trie.insert(argument);
                    break;
                case "REMOVE":
                    trie.delete(argument);
                    break;
                case "SEARCH":
                    List<String> words = trie.searchByPrefix(argument);
                    if (words.isEmpty()) {
                        System.out.println("404 Not found");
                    } else {
                        Collections.sort(words);
                        for (String word : words) {
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

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    class TrieNode {
        Map<Character, TrieNode> child;
        boolean isEnd;

        public TrieNode() {
            child = new HashMap<>();
            isEnd = false;
        }
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

    private boolean delete(TrieNode current, String word, int index) {
        // 문자열의 끝에 도달한 경우
        if (index == word.length()) {
            // 현재 노드가 단어의 끝이 아니라면, 단어가 Trie에 존재하지 않음
            if (!current.isEnd) {
                return false;
            }
            // 현재 노드가 단어의 끝이라면, 단어 삭제 표시
            current.isEnd = false;
            // 현재 노드가 더 이상 다른 단어의 일부가 아니고 자식 노드도 없는 경우, 현재 노드를 삭제할 수 있음
            return current.child.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.child.get(ch);
        // 현재 문자에 해당하는 노드가 없는 경우, 단어가 Trie에 존재하지 않음
        if (node == null) {
            return false;
        }
        // 재귀적으로 자식 노드로 이동하여 삭제 작업 수행
        boolean shouldDelete = delete(node, word, index + 1);
        // 자식 노드를 삭제해야 하는 경우
        if (shouldDelete && !node.isEnd) {
            // 현재 노드에서 자식 노드 삭제
            current.child.remove(ch);
            // 현재 노드가 다른 단어의 일부가 아니고 자식 노드도 없는 경우, 현재 노드를 삭제할 수 있음
            return current.child.isEmpty();
        }
        return false;
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
        findAllWords(current, new StringBuilder(prefix), results);
        return results;
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

    private void findAllWords(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEnd) {
            results.add(prefix.toString());
        }
        for (char ch : node.child.keySet()) {
            prefix.append(ch);
            findAllWords(node.child.get(ch), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}

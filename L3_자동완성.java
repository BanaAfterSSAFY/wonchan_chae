import java.util.*;

class Solution {
    static class TrieNode {
        Map<Character, TrieNode> cn = new HashMap();
        int cnt;
        boolean flag;
    }

    static class Trie {
        TrieNode root;
        
        Trie() {
            root = new TrieNode();
        }
        
        void insert(String word) {
            TrieNode node = this.root;
            
            for(int i = 0; i < word.length(); i++) {
                node.cnt++;
                node = node.cn.computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            node.cnt++;
            node.flag = true;
        }
        
        int getMinTypingCount(String word) {
            TrieNode node = this.root;
            
            int l = word.length();
            int end = l;
            
            for(int i = 0; i < l; i++) {
                if(node.cnt == 1) {
                    end = i;
                    break;
                }
                node = node.cn.get(word.charAt(i));
            }
            return end;
        }

    }

    public int solution(String[] words) {

        int answer = 0;
        Trie trie = new Trie();
        for(String at : words) {
            trie.insert(at);
        }

        for(String at : words) {
            answer += trie.getMinTypingCount(at);
        }

        return answer;
    }
}
package algorithms

import java.util.*


//internal class Solution {
//    internal inner class TrieNode {
//         val children: Array<TrieNode?> = arrayOfNulls(26)
//        var word: String? = null
//
//    }
//
//    private fun buildTrie(words: Array<String>): TrieNode {
//        val root = TrieNode()
//        for (word in words) {
//            var runner: TrieNode? = root
//            for (c in word.toCharArray()) {
//                if (runner!!.children[c - 'a'] == null) {
//                    runner.children[c - 'a'] = TrieNode()
//                }
//                runner = runner.children[c - 'a']
//            }
//            runner!!.word = word
//        }
//        return root
//    }
//
//    fun findWords(board: Array<CharArray>, words: Array<String>): List<String?> {
//        val root = buildTrie(words)
//        val result: MutableList<String?> = ArrayList()
//        for (i in board.indices) {
//            for (j in 0 until board[i].size) {
//                helper(board, i, j, root, result)
//            }
//        }
//        return result
//    }
//
//    private fun helper(board: Array<CharArray>, row: Int, col: Int, root: TrieNode?, result: MutableList<String?>) {
//        if (root!!.word != null) {
//            result.add(root.word)
//            root.word = null
//            return
//        }
//        if (row < 0 || col < 0 || row >= board.size || col >= board[row].size || board[row][col] == '.' || root.children[board[row][col] - 'a'] == null) {
//            return
//        }
//        val c = board[row][col]
//        board[row][col] = '.'
//        helper(board, row + 1, col, root.children[c - 'a'], result)
//        helper(board, row - 1, col, root.children[c - 'a'], result)
//        helper(board, row, col + 1, root.children[c - 'a'], result)
//        helper(board, row, col - 1, root.children[c - 'a'], result)
//        board[row][col] = c
//    }
//}
///////////////////////////////////////////////////////////////////////////
// World Ladder 2
fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
    val wordSet: MutableSet<String> = HashSet(wordList)
    if (!wordSet.contains(endWord)) return ArrayList<List<String>>()

    // {"hit": ["hot"], "hot": ["dot", "lot"], ...}
    val graph: MutableMap<String, MutableList<String>?> = HashMap()

    // build graph from beginWord -> endWord
    if (!bfs(beginWord, endWord, wordSet, graph)) return ArrayList<List<String>>()
    val ans: MutableList<List<String>> = ArrayList()
    val path: MutableList<String> = ArrayList(Arrays.asList(beginWord))
    dfs(graph, beginWord, endWord, path, ans)
    return ans
}

 fun bfs(beginWord: String, endWord: String, wordSet: MutableSet<String>,
                     graph: MutableMap<String, MutableList<String>?>): Boolean {
    var currentLevelWords: MutableSet<String> = HashSet()
    currentLevelWords.add(beginWord)
    var reachEndWord = false
    while (!currentLevelWords.isEmpty()) {
        for (word in currentLevelWords) wordSet.remove(word)
        val nextLevelWords: MutableSet<String> = HashSet()
        for (parent in currentLevelWords) {
            graph.putIfAbsent(parent, ArrayList())
            for (child in getChildren(parent, wordSet)) {
                if (wordSet.contains(child)) {
                    nextLevelWords.add(child)
                    graph[parent]!!.add(child)
                }
                if (child == endWord) reachEndWord = true
            }
        }
        if (reachEndWord) return true
        currentLevelWords = nextLevelWords
    }
    return false
}

 fun getChildren(parent: String, wordSet: Set<String>): List<String> {
    val children: MutableList<String> = ArrayList()
    val sb = StringBuilder(parent)
    for (i in sb.indices) {
        val cache = sb[i]
        var c = 'a'
        while (c <= 'z') {
            if (c == cache) {
                ++c
                continue
            }
            sb.setCharAt(i, c)
            val child = sb.toString()
            if (wordSet.contains(child)) children.add(child)
            ++c
        }
        sb.setCharAt(i, cache)
    }
    return children
}

 fun dfs(graph: Map<String, MutableList<String>?>, word: String, endWord: String,
                     path: MutableList<String>, ans: MutableList<List<String>>) {
    if (word == endWord) {
        ans.add(ArrayList(path))
        return
    }
    if (!graph.containsKey(word)) return
    for (child in graph[word]!!) {
        path.add(child)
        dfs(graph, child, endWord, path, ans)
        path.removeAt(path.size - 1)
    }
}
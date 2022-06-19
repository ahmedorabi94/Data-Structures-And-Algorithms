package algorithms

import java.util.*


class Solution {
    class TrieNode {
        var children = arrayOfNulls<TrieNode>(26)
        var word: String? = null
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String?> {
        for (word in words) insert(word)
        val ans: MutableList<String?> = ArrayList()
        for (i in board.indices) for (j in board[0].indices) dfs(board, i, j, root, ans)
        return ans
    }

    private val root = TrieNode()
    private fun insert(word: String) {
        var node: TrieNode? = root
        for (c in word.toCharArray()) {
            if (node!!.children[c - 'a'] == null) node.children[c - 'a'] = TrieNode()
            node = node.children[c - 'a']
        }
        node!!.word = word
    }

    private fun dfs(board: Array<CharArray>, i: Int, j: Int, node: TrieNode, ans: MutableList<String?>) {
        if (i < 0 || i == board.size || j < 0 || j == board[0].size) return
        if (board[i][j] == '*') return
        val c = board[i][j]
        val child = node.children.get(c - 'a') ?: return
        if (child.word != null) {
            ans.add(child.word)
            child.word = null
        }
        board[i][j] = '*'
        dfs(board, i + 1, j, child, ans)
        dfs(board, i - 1, j, child, ans)
        dfs(board, i, j + 1, child, ans)
        dfs(board, i, j - 1, child, ans)
        board[i][j] = c
    }
}
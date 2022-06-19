package algorithms

import java.util.*

fun main() {

}

/////////////////////////////////////////////////////
//200. Number of Islands
fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty() || grid[0].isEmpty()) {
        return 0
    }
    val m: Int = grid.size
    val n: Int = grid[0].size
    var count = 0

    for (i in 0 until m) {
        for (j in 0 until n) {

            if (grid[i][j] == '1') {
                count++
                dfs(grid, m, n, i, j)
            }

        }
    }

    return count
}
fun dfs(grid: Array<CharArray>, m: Int, n: Int, i: Int, j: Int) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
        return
    }
    grid[i][j] = 'X'
    dfs(grid, m, n, i - 1, j)
    dfs(grid, m, n, i + 1, j)
    dfs(grid, m, n, i, j - 1)
    dfs(grid, m, n, i, j + 1)
}

/////////////////////////////////////////////////////////////////////
//79. Word Search
fun exist(board: Array<CharArray>, word: String): Boolean {
    if (board.isEmpty() || board[0].isEmpty() || word == "") {
        return false
    }

    for (i in board.indices) {  // rows
        for (j in board[0].indices) {    // cols
            if (search(board, word, i, j, 0)) {   // if true
                return true
            }
        }
    }
    return false
}
fun search(board: Array<CharArray>, word: String, i: Int, j: Int, matched: Int): Boolean {
    if (matched == word.length) {
        return true
    }
    if (i < 0 || j < 0 || i >= board.size || j >= board[0].size || board[i][j] != word[matched]) {
        return false
    }
    board[i][j] = '#'
    val result =
            search(board, word, i - 1, j, matched + 1) ||
                    search(board, word, i + 1, j, matched + 1) ||
                    search(board, word, i, j - 1, matched + 1) ||
                    search(board, word, i, j + 1, matched + 1)

    board[i][j] = word[matched]
    return result
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
private class TrieNode {
    var isLeaf: Boolean
    var children: HashMap<Char, TrieNode?>
    var word: String? = null

    init {
        isLeaf = false
        children = HashMap()
    }
}

class Trie {
    private val root: TrieNode
    fun add(word: String) {
        var children = root.children
        for (i in 0 until word.length) {
            val c = word[i]
            if (!children.containsKey(c)) {
                val node = TrieNode()
                children[c] = node
            }
            val node = children[c]
            children = node!!.children
            if (i == word.length - 1) {
                node.isLeaf = true
            }
        }
    }

    private fun searchNode(pre: String): TrieNode? {
        var children = root.children
        var node: TrieNode? = root
        for (i in 0 until pre.length) {
            val c = pre[i]
            if (!children.containsKey(c)) {
                return null
            }
            node = children[c]
            children = node!!.children
        }
        return node
    }

    fun search(word: String): Boolean {
        val node = searchNode(word)
        return node != null && node.isLeaf == true
    }

    fun startsWith(prefix: String): Boolean {
        val node = searchNode(prefix)
        return node != null
    }

    init {
        root = TrieNode()
    }
}

fun findWords(board: Array<CharArray>, words: Array<String>): MutableList<String> {
    val result: MutableSet<String> = HashSet()
    val pt = Trie()
    for (i in words.indices) {
        pt.add(words[i])
    }
    if (board == null || board.size == 0 || board[0].size == 0) {
        return LinkedList<String>(result)
    }
    val visited = Array(board.size) { BooleanArray(board[0].size) }
    for (i in board.indices) {
        for (j in 0 until board[0].size) {
            dfs2(pt, i, j, board, visited, "", result)
        }
    }
    return LinkedList<String>(result)
}

fun dfs2(pt: Trie, i: Int, j: Int, board: Array<CharArray>, visited: Array<BooleanArray>, s: String, result: MutableSet<String>) {
    var s = s
    if (i < 0 || i >= visited.size || j < 0 || j >= visited[0].size) {
        return
    }
    if (visited[i][j] == true) {
        return
    }
    s += board[i][j]
    if (!pt.startsWith(s)) {
        return
    }
    if (pt.search(s)) {
        result.add(s)
    }
    visited[i][j] = true
    dfs2(pt, i - 1, j, board, visited, s, result)
    dfs2(pt, i, j - 1, board, visited, s, result)
    dfs2(pt, i + 1, j, board, visited, s, result)
    dfs2(pt, i, j + 1, board, visited, s, result)
    visited[i][j] = false
}
//////////////////////////////////////////////////////////////////////////////////////////
//417. Pacific Atlantic Water Flow
fun pacificAtlantic(heights: Array<IntArray>?): List<List<Int>> {
    if (heights == null || heights.isEmpty()) {
        return Collections.emptyList()
    }
    val result: MutableList<List<Int>> = ArrayList()
    val rows = heights.size
    val cols: Int = heights[0].size
    val pacificVisited = Array(rows) { BooleanArray(cols) }
    val atlanticVisited = Array(rows) { BooleanArray(cols) }
    for (i in 0 until rows) {
        dfs(heights, i, 0, -1, pacificVisited)
        dfs(heights, i, cols - 1, -1, atlanticVisited)
    }
    for (j in 0 until cols) {
        dfs(heights, 0, j, -1, pacificVisited)
        dfs(heights, rows - 1, j, -1, atlanticVisited)
    }
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                result.add(Arrays.asList(i, j))
            }
        }
    }
    return result
}
fun dfs(matrix: Array<IntArray>, x: Int, y: Int, prevWaterHeight: Int, visited: Array<BooleanArray>) {
    if (x < 0 || y < 0 || x >= matrix.size || y >= matrix[x].size || visited[x][y]
            || prevWaterHeight > matrix[x][y]) {
        return
    }
    val height = matrix[x][y]
    visited[x][y] = true
    dfs(matrix, x - 1, y, height, visited)
    dfs(matrix, x + 1, y, height, visited)
    dfs(matrix, x, y - 1, height, visited)
    dfs(matrix, x, y + 1, height, visited)
    return
}

///////////////////////////////////////////////////////////////////////
///39. Combination Sum
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    if (candidates.isEmpty()) {
        return Collections.emptyList()
    }
    val result: MutableList<List<Int>> = ArrayList()
    dfs(candidates, target, 0, ArrayList(), result)
    return result
}
fun dfs(candidates: IntArray, target: Int, idx: Int, tempResult: MutableList<Int>, result: MutableList<List<Int>>) {
    if (target < 0) {
        return
    }
    if (target == 0) {
        result.add(ArrayList(tempResult))
        return
    }
    for (i in idx until candidates.size) {
        tempResult.add(candidates[i])
        dfs(candidates, target - candidates[i], i, tempResult, result)
        tempResult.removeAt(tempResult.size - 1)
    }
}
////////////////////////////////////////////////////////////////////////////////////////////
//212. Word Search II

class TrieNodeX {
    var children: Array<TrieNodeX?> = arrayOfNulls(26)
    var word: String? = null

}

fun buildTrie(words: Array<String>): TrieNodeX {
    val root = TrieNodeX()
    for (word in words) {
        var runner: TrieNodeX? = root
        for (c in word.toCharArray()) {
            if (runner!!.children[c - 'a'] == null) {
                runner.children[c - 'a'] = TrieNodeX()
            }
            runner = runner.children.get(c - 'a')
        }
        runner?.word = word
    }
    return root
}

fun findWordsx(board: Array<CharArray>, words: Array<String>): List<String>? {
    val root = buildTrie(words)
    val result: MutableList<String> = ArrayList()
    for (i in board.indices) {
        for (j in board[i].indices) {
            helper(board, i, j, root, result)
        }
    }
    return result
}

fun helper(board: Array<CharArray>, row: Int, col: Int, root: TrieNodeX?, result: MutableList<String>) {
    if (root?.word != null) {
        result.add(root.word!!)
        root.word = null
        return
    }
    if (row < 0 || col < 0 || row >= board.size || col >= board[row].size || board[row][col] == '.' || root!!.children.get(board[row][col] - 'a') == null) {
        return
    }
    val c = board[row][col]
    board[row][col] = '.'
    helper(board, row + 1, col, root.children[c - 'a'], result)
    helper(board, row - 1, col, root.children[c - 'a'], result)
    helper(board, row, col + 1, root.children[c - 'a'], result)
    helper(board, row, col - 1, root.children[c - 'a'], result)
    board[row][col] = c
}
/////////////////////////////////////////////////////////////////////////////
//529. Minesweeper
fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
    if (board[click[0]][click[1]] == 'M') {
        board[click[0]][click[1]] = 'X'
        return board
    }
    dfs(board, click[0], click[1])
    return board
}

private val dirs = arrayOf(intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(-1, 1), intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(1, -1), intArrayOf(1, 0), intArrayOf(1, 1))

fun dfs(board: Array<CharArray>, i: Int, j: Int) {
    if (i < 0 || i == board.size || j < 0 || j == board[0].size) return
    if (board[i][j] != 'E') return
    val minesCount = getMinesCount(board, i, j)
    board[i][j] = if (minesCount == 0) 'B' else ('0'.toInt() + minesCount).toChar()
    if (minesCount == 0) for (dir in dirs) dfs(board, i + dir[0], j + dir[1])
}

fun getMinesCount(board: Array<CharArray>, i: Int, j: Int): Int {
    var minesCount = 0
    for (dir in dirs) {
        val x = i + dir[0]
        val y = j + dir[1]
        if (x < 0 || x == board.size || y < 0 || y == board[0].size) continue
        if (board[x][y] == 'M') ++minesCount
    }
    return minesCount
}
//////////////////////////////////////////////////////////////////////
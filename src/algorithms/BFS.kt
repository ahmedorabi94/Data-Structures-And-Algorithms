package algorithms

import java.util.*

///////////////////////////////////////////////////
//207. Course Schedule
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>?): Boolean {
    if (prerequisites == null || prerequisites.isEmpty()) {
        return true
    }
    val graph: MutableMap<Int, MutableSet<Int>?> = HashMap()
    val indegrees = IntArray(numCourses)
    for (p in prerequisites) {
        graph.putIfAbsent(p[1], HashSet())
        graph[p[1]]!!.add(p[0])
        indegrees[p[0]]++
    }
    val q: Queue<Int> = LinkedList()
    for (i in indegrees.indices) {
        if (indegrees[i] == 0) {
            q.offer(i)
        }
    }
    var count = 0
    while (!q.isEmpty()) {
        val curr = q.poll()
        if (indegrees[curr] == 0) {
            ++count
        }
        if (!graph.containsKey(curr)) {
            continue
        }
        for (neighbour in graph[curr]!!) {
            indegrees[neighbour]--
            if (indegrees[neighbour] == 0) {
                q.offer(neighbour)
            }
        }
    }
    return count == numCourses
}
////////////////////////////////////////////////////
//210. Course Schedule II
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray? {
    val graph: MutableMap<Int, MutableSet<Int>?> = HashMap()
    val indegrees = IntArray(numCourses)

    for (p in prerequisites) {
        graph.putIfAbsent(p[1], HashSet())
        graph[p[1]]!!.add(p[0])
        indegrees[p[0]]++
    }

    val result = IntArray(numCourses)
    val q: Queue<Int> = LinkedList()
    for (i in indegrees.indices) {
        if (indegrees[i] == 0) {
            q.offer(i)
        }
    }

    var idx = 0
    while (!q.isEmpty()) {
        val curr: Int = q.poll()
        if (indegrees[curr] == 0) {
            result[idx++] = curr
        }
        if (!graph.containsKey(curr)) {
            continue
        }
        for (neighbour in graph[curr]!!) {
            indegrees[neighbour]--
            if (indegrees[neighbour] == 0) {
                q.offer(neighbour)
            }
        }
    }

    return if (idx == numCourses) result else IntArray(0)
}
//////////////////////////////////////////////////////////////////////////////////////////
// 127. Word Ladder
fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    if (beginWord == endWord) {
        return 1
    }
    val dict: MutableSet<String> = HashSet(wordList)
    val q: Queue<String> = LinkedList()
    q.offer(beginWord)
    var steps = 1

    while (!q.isEmpty()) {
        val level: Int = q.size
        for (i in 0 until level) {
            val s = q.poll()
            if (s == endWord) {
                return steps
            }
            val letters = s.toCharArray()
            for (j in s.indices) {
                val prevCh = letters[j]
                var ch = 'a'
                while (ch <= 'z') {
                    letters[j] = ch
                    val nextWord = String(letters)
                    if (dict.contains(nextWord)) {
                        q.offer(nextWord)
                        dict.remove(nextWord)
                    }
                    ch++
                }
                letters[j] = prevCh
            }
        }
        ++steps
    }
    return 0
}
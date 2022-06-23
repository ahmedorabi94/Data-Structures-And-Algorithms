package algorithms

//17
fun letterCombinations(digits: String): List<String> {

    if (digits.isEmpty()){
        return emptyList()
    }

    //23
    val map = HashMap<Char,String>()
    map.put('1', "")
    map.put('2', "abc")
    map.put('3', "def")
    map.put('4', "ghi")
    map.put('5', "jkl")
    map.put('6', "mno")
    map.put('7', "pqrs")
    map.put('8', "tuv")
    map.put('9', "wxyz")

    val list = ArrayList<String>()
    for (c in digits){
        //  println(map.get(c))
        list.add(map.get(c)!!)
    }
    val sb = StringBuilder()
    val result = ArrayList<String>()

    if (list.size == 1){
        for (first in list[0]){
            result.add(first + "")
        }

        return result
    }

    for (first in list[0]){
        for (sec in list[1]){
            val text = "$first$sec"
            sb.append(text)
            result.add(text)
        }
    }

    //println(sb.toString())



    return result
}
/**
 * Created by keith on 06/12/2016.
 */

fun main(args: Array<String>) {

    //val dictdir = "/Users/keith/Code/personal/wordnet/dict/"
    val dictdir = "/Users/keithm/Documents/workspace/Kotlin/wordnet/dict/"

//    val listReader = ListReader()
//    val adjs = listReader.readAndFilter(dictdir + "adj.krm")
//    val nouns = listReader.readAndFilter(dictdir + "nouns.krm")
//    val advs = listReader.readAndFilter(dictdir + "advs.krm")
//    val verbs = listReader.readAndFilter(dictdir + "verbs.krm")


    // This is the same as nouns.map( word -> println(word) )
    // nouns.map(::println)

//    val wordTools = WordListTools()
//    val eg = EntropyGenerator()
    val pwgen = PasswordGenerator(dictdir)
    for (i in 8..16) {
//        //println(wordTools.adjNounPairOfTotalLength(i))
//        val (adj,noun) = wordTools.pairOfTotalLength(i, adjs, nouns)
//        println(eg.addSuffix(eg.replaceOnePunct(eg.combineCap(adj, noun))))
////        println("$adj${noun.capitalize()}")
//        val (verb,adv) = wordTools.pairOfTotalLength(i, verbs, advs)
//        println(eg.replaceOneNumber(eg.combineChar(verb, adv)))
////        println("$verb${adv.capitalize()}")
        println(pwgen.generatePassword(i).password)
    }
}

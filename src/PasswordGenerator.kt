import java.security.SecureRandom

/**
 * Created by keith on 08/12/2016.
 */
class PasswordGenerator(val dictdir: String)
{
    // Hmm.. usually I would initialise these inside a constructor
    // where I would be able to handle exceptions explicitly.. I
    // am unclear how Kotlin handles this.
    private val random = SecureRandom()

    private val listReader = ListReader()
    private val adjs = listReader.readAndFilter(dictdir + "adj.krm")
    private val nouns = listReader.readAndFilter(dictdir + "nouns.krm")
    private val advs = listReader.readAndFilter(dictdir + "advs.krm")
    private val verbs = listReader.readAndFilter(dictdir + "verbs.krm")

    private val eg = EntropyGenerator()
    private val wordTools = WordListTools()

    data class PasswordResult(
            var password: String,
            var term1: String,
            var term2: String,
            var useVerb: Boolean,
            var combineType: Boolean,
            var length: Int
    )


    fun generatePassword(length: Int): PasswordResult {
        // Combine with camel-case (true) or punctuation (false)?
        // min here is 8 but then has to be camel cased
        val combineType = length < 9 || random.nextInt(10) > 2

        // Length of base words
        val l = if (combineType) length - 2 else length - 3

        // nouns, or verbs?
        val useVerb = random.nextInt(10) > 7
        val (term1, term2) =
            if (useVerb)
                wordTools.pairOfTotalLength(l, verbs, advs)
            else
                wordTools.pairOfTotalLength(l, adjs, nouns)

        val password =
            if (combineType) // camelcase
                eg.ensureUppercase(
                        eg.addSuffix(
                                eg.replaceOnePunct(
                                        eg.combineCap(term1, term2))))
            else // join with punctuation
                eg.ensureUppercase(
                        eg.addSuffix(
                                eg.replaceOneNumber(
                                        eg.combineChar(term1, term2))))

        return PasswordResult(password, term1, term2, useVerb, combineType, length)
    }
}
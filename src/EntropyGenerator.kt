import java.security.SecureRandom

/**
 * Created by keith on 07/12/2016.
 */
class EntropyGenerator
{
    private val random = SecureRandom()

    private val combiners = listOf("_", "-", "~", "=", ".", ",", "%")

    private val punctuation = "!@#$%^&*()-_=+[]{};:~/\\|?<>,.".toList()

    private val punctuationMappings = mapOf(
            'a' to '@',
            'c' to '(',
            'd' to ')',
            'i' to '!',
            'k' to '<',
            'l' to '|',
            'n' to '^',
            'o' to '*',
            'p' to '?',
            's' to '$',
            't' to '+'
    )

    private val numberMappings = mapOf(
            'a' to '4',
            'b' to '6',
            'e' to '3',
            'g' to '9',
            'i' to '1',
            'l' to '1',
            'o' to '0',
            's' to '5',
            't' to '7',
            'z' to '2'
    )

    fun combineChar(term1: String, term2: String): String {
        val combiner = combiners.get(random.nextInt(combiners.size))
        return "$term1$combiner$term2"
    }

    fun combineCap(term1: String, term2: String): String
            = "$term1${term2.capitalize()}"

    fun replaceOneChar(term: String, replaceMap: Map<Char, Char>): String {
        val positions = (0..(term.length - 1)).toMutableList()

        var returnTerm = term

        var replaced = false
        while (positions.size > 0 && !replaced) {
            val rn = random.nextInt(positions.size)
            val pos = positions.get(rn)
            val ch = term.get(pos)
            positions.removeAt(rn)

            if (replaceMap.containsKey(ch)) {
                replaced = true
                returnTerm = term.replaceRange(pos, pos + 1, replaceMap[ch].toString())
            }
        }

        return returnTerm
    }

    fun replaceOnePunct(term: String) = replaceOneChar(term, punctuationMappings)
    fun replaceOneNumber(term: String) = replaceOneChar(term, numberMappings)

    /**
     * Adds a suffix to the string - if the string has no punctuation
     * characters already, it will include one punctuation character in
     * the suffix, otherwise it will use just numbers
     */
    fun addSuffix(term: String, size: Int = 2): String {
        var returnTerm =
            if (containsPunctuation(term)) {
                appendNumbers(term, size)
            } else {
                val pterm = appendPunct(term)
                appendNumbers(pterm, size - 1)
            }


        return returnTerm
    }

    fun containsPunctuation(term: String): Boolean =
            term.find { ch -> punctuation.contains(ch) } != null

    fun appendPunct(term: String): String =
            term.plus(punctuation.get(random.nextInt(punctuation.size)))

    fun appendNumbers(term: String, size: Int): String {
        var returnTerm = term
        for (i in 1..size) {
            returnTerm = returnTerm.plus(random.nextInt(10))
        }

        return returnTerm
    }

    fun containsUppercase(term: String): Boolean =
            (term.find { ch -> ch.isUpperCase() } != null)

    /**
     * Pick one lowercase character and replace it with an uppercase
     * equivalent
     */
    fun randomlyUppercase(term: String): String {
        var replaced = false
        var returnTerm = term
        while (!replaced) {
            val pos = random.nextInt(term.length)
            if (term[pos].isLowerCase()) {
                val replacement = term[pos].toUpperCase().toString()
                returnTerm = term.replaceRange(pos, pos + 1, replacement)
                replaced = true
            }
        }

        return returnTerm
    }

    /**
     * Ensure there's at least one uppercase char, if not,
     * pick one at random and uppercase it
     */
    fun ensureUppercase(term: String): String {
        return if (!containsUppercase(term))
            randomlyUppercase(term)
        else
            term
    }

}
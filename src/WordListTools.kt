import java.security.SecureRandom

/**
 * Created by keith on 06/12/2016.
 */
class WordListTools {
    private val random = SecureRandom()
    private val allChars =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*(),./\\|:;~+=-_?".toList()

    private fun randomTermShorterThan(n: Int, list: List<String>): String {
        val filtered = list.filter { x -> x.length < n}
        val rand: Int = random.nextInt(filtered.size)
        return filtered.get(rand)
    }

    private fun randomTermOfLength(n: Int, list: List<String>): String {
        val filtered = list.filter { x -> x.length == n}
        if (filtered.size == 0) {
            // no words of length n available, try n-1 and add a character:
            return randomTermOfLength(n - 1, list).plus(randomCharacter())
        }
        else {
            val rand: Int = random.nextInt(filtered.size)
            return filtered.get(rand)
        }
    }

    private fun randomCharacter(): Char = allChars[random.nextInt(allChars.size)]

    fun pairOfTotalLength(n: Int, list1: List<String>, list2: List<String>):
            Pair<String, String>
    {
        if (n < 6) throw IllegalArgumentException("${n} is too short")
        if (n > 40) throw IllegalArgumentException("${n} is too long")

        var result: Pair<String, String>

        // This balances it a little better for pwds greater than 30 characters
        // It doesn't work so well otherwise as the first term is always a lot shorter,
        // and there aren't so many super-long second terms.
        if (random.nextInt(3) < 2) {
            val term1 = randomTermShorterThan((n - 2) - random.nextInt(n / 3), list1)
            val term2 = randomTermOfLength(n - term1.length, list2)
            result = Pair(term1, term2)
        } else {
            val term2 = randomTermShorterThan((n - 2) - random.nextInt(n / 3), list2)
            val term1 = randomTermOfLength(n - term2.length, list1)
            result = Pair(term1, term2)
        }

        return result
    }

}
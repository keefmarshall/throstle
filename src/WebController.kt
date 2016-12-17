import org.wasabifx.wasabi.routing.routeHandler
import java.security.SecureRandom

/**
 * Route handlers for the password generator REST API
 *
 * Created by keith on 14/12/2016.
 */
class WebController(dictdir: String)
{
    val random = SecureRandom()
    val pwgen = PasswordGenerator(dictdir)

    val randomLengthPassword = routeHandler {
        val passRes = pwgen.generatePassword(random.nextInt(13) + 8)
        if (request.queryParams["full"] != null)
            response.send(passRes, "application/json")
        else
            response.send(passRes.password, "text/plain")
    }

    val insults = listOf( "Silly sausage", "Don't give up the day job", "You muppet", "How rude",
            "Your mother was a hamster", "Your father smelt of elderberries",
            "You empty-headed animal food trough wiper")

    val fixedLengthPassword = routeHandler {
        val lengthParam = request.routeParams["length"]
        val length = try { lengthParam?.toInt() ?: 0 } catch(e: NumberFormatException) { -1 }

        if (length <= 0)
        {
            response.setStatus(400, "Bad Request")
            val insult = insults[random.nextInt(insults.size)]
            response.send("$insult, that is not a helpful password length. Try again.", "text/plain")
        }
        else if (length < 8 || length > 40)
        {
            response.setStatus(400, "Bad Request")
            response.send("Length must be between 8 and 40", "text/plain")
        }
        else
        {
            val passRes = pwgen.generatePassword(length)
            if (request.queryParams["full"] != null)
                response.send(passRes, "application/json")
            else
                response.send(passRes.password, "text/plain")
        }
    }

}
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

    val fixedLengthPassword = routeHandler {
        val lengthParam = request.routeParams["length"]
        val length = lengthParam?.toInt() ?: 0
        if (length < 8 || length > 40)
        {
            response.setStatus(400, "Invalid length")
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
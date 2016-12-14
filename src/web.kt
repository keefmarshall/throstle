/**
 * Created by keith on 12/12/2016.
 */

import org.wasabifx.wasabi.app.AppServer
import org.wasabifx.wasabi.interceptors.serveStaticFilesFromFolder


fun main(args: Array<String>)
{
    val dictdir = if (args.size > 0) args[0] else "/Users/keith/Code/personal/wordnet/dict/"
    val publicdir = if (args.size > 1) args[1] else "/Users/keith/IdeaProjects/throstle/public"

    val server = AppServer()
    val controller = WebController(dictdir)

    // INTERCEPTORS
    server.serveStaticFilesFromFolder(publicdir, true)

    // ROUTES
    server.get("/password", controller.randomLengthPassword)
    server.get("/password/:length", controller.fixedLengthPassword)

    server.start()
}


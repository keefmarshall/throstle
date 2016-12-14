import java.io.File

/**
 * Reads a list of strings from a file, one string per line
 *
 * Created by keith on 06/12/2016.
 */
class ListReader {
    fun readList(path: String): List<String> = File(path).readLines()

    fun readAndFilter(path: String): List<String> {
        val list = readList(path)

        return list
                //.filter { x -> x.elementAt(0).isLowerCase() }
                .map {x -> x.replace(Regex("[_-]"), "")}
    }
}


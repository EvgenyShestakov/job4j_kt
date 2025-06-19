package ru.job4j.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.InputStream
import java.io.PrintWriter

fun main() {
    val logFileStream: InputStream = object {}
        .javaClass.classLoader.getResourceAsStream("logs.txt")
        ?: error("logs.txt not found in resources")
    runBlocking {
        val logs = readLogs(logFileStream).toList()
        val groupedLogs = groupLogsByDateAndLevel(logs)
        writeLogsToFiles(groupedLogs)
    }
}

data class LogEntry(
    val date: String,
    val time: String,
    val level: String,
    val message: String,
)

fun readLogs(inputStream: InputStream): Flow<LogEntry> = flow {
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            parseLogLine(line)?.let { emit(it) }
        }
    }
}

fun groupLogsByDateAndLevel(
    logs: List<LogEntry>,
): Map<String,
        Map<String, List<LogEntry>>> {
    return logs.groupBy { it.level }
        .mapValues { (_, entries) -> entries.groupBy { it.date } }
}

fun writeLogsToFiles(groupedLogs: Map<String, Map<String, List<LogEntry>>>) {
    for ((level, byDate) in groupedLogs) {
        writeLogFile(level, byDate)
    }
}

private fun parseLogLine(line: String): LogEntry? {

    val regex = Regex(
        """
        |^(\d{4}-\d{2}-\d{2}|\d{2}\.\d{2}\.\d{4}|\d{4}/\d{2}/\d{2})
        |(?:[ T])
        |(\d{2}:\d{2}:\d{2})
        |(?:\.\d+)?
        |\s+
        |(\w+)
        |\s+(.+)$"""
            .trimMargin()
    )
    val match = regex.find(line.trim())
    return if (match != null) {
        val groups = match.groupValues
        val date = groups[1]
        val time = groups[2]
        val level = groups[3]
        val message = groups[4]
        LogEntry(
            date.replace(".", "-").replace("/", "-"),
            time,
            level.uppercase(),
            message
        )
    } else {
        null
    }
}

private fun writeLogFile(level: String, byDate: Map<String, List<LogEntry>>) {
    val file = File("$level.log")
    file.printWriter().use { writer ->
        byDate.toSortedMap().forEach { (date, entries) ->
            writer.println(date)
            writeEntries(writer, entries)
            writer.println()
        }
    }
}

private fun writeEntries(writer: PrintWriter, entries: List<LogEntry>) {
    entries.sortedBy { it.time }.forEach { entry ->
        writer.println("    ${entry.time} ${entry.message}")
    }
}
package ir.aut.ceit.app.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * using for write to a log file
 */
public class OutputFileWriter {
    /**
     * gives a table and saves it to a file
     *
     * @param table given string table
     * @param type  check forecast or current
     * @throws IOException check errors
     */
    public void saveToLogFile(String table, String type) throws IOException {
        new File(type).mkdir();
        File file = new File(type + "\\" + new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(table);
        bufferedWriter.close();
    }
}

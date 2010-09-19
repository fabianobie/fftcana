package br.ufc.cana.fft.math;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

//This custom formatter formats parts of a log record to a single line
public class TimeFormatter extends Formatter
{
        // This method is called for every log records
        public String format(LogRecord rec)
        {
                //return rec.getLevel().toString()+": "+rec.getResourceBundleName()+":"+rec.getMessage()+"\n";
            return " \n"+rec.getMessage()+"\n";
        }

        // This method is called just after the handler using this
        // formatter is created
        public String getHead(Handler h)
        {
                return "Logger has been started\n";
        }

        // This method is called just after the handler using this
        // formatter is closed
        public String getTail(Handler h)
        {
                return "Logger has been stopped\n";
        }
}

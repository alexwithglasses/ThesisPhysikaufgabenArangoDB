package outputLatex;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;


public class AufgabeNachTeXDatei {

        public static String writeStringToTexFile(LinkedList<String> writeToTex){

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            Path path = Paths.get("./latexoutput/Aufgabe" + timeStamp + ".tex");

            String ls = System.lineSeparator();

            String fileHead = "\\documentclass{article}"+ ls +
                    ls +
                    "\\usepackage{amsmath}" + ls +
                    "\\usepackage[ngerman]{babel}"+ ls +
                    "\\usepackage[utf8]{inputenc}" + ls +
                    ls +
                    "\\begin{document}" + ls;

            String fileFoot = "\\end{document}";

            writeToTex.addFirst(fileHead);
            writeToTex.addLast(fileFoot);

            try {
                for (String line: writeToTex
                     ) {
                    Files.writeString(path, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            } catch (IOException x) {
                return path.toString();
            }
            return path.toString();
        }
}


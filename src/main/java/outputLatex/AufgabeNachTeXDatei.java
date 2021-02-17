package outputLatex;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


public class AufgabeNachTeXDatei {

        public static boolean writeStringToTexFile(List<String> writeToTex){

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            Path path = Paths.get("./latexoutput/Aufgabe" + timeStamp + ".tex");

            String fileHead = "\\documentclass{article}\n" +
                    "\n" +
                    "\\usepackage{amsmath}\n" +
                    "\\usepackage[ngerman]{babel}\n" +
                    "\\usepackage[utf8]{inputenc}\n" +
                    "\n" +
                    "\\begin{document}";

            String fileFoot = "\\end{document}";


            try {
                Files.write(path,
                        fileHead.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE
                );

                for (String aufgabe: writeToTex
                     ) {
                    Files.write(path, aufgabe.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                }

                Files.write(path,fileFoot.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);


            } catch (IOException x) {
                return false;
            }
            return true;
        }

}


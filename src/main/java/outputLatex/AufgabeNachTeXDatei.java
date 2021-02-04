package outputLatex;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.SimpleDateFormat;

import java.util.Date;


public class AufgabeNachTeXDatei {

        public static boolean writeStringToTexFile(String writeToTex){

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            Path path = Paths.get("./latexoutput/Aufgabe" + timeStamp + ".tex");

            try {
                Files.write(path, writeToTex.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            } catch (IOException x) {
                System.out.println("file not created");
                return false;
            }
            return true;
        }

}


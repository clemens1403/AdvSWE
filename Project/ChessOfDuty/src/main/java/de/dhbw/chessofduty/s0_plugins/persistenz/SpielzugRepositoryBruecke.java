package de.dhbw.chessofduty.s0_plugins.persistenz;

import de.dhbw.chessofduty.s3_domain_code.spielzug.Spielzug;
import de.dhbw.chessofduty.s3_domain_code.spielzug.SpielzugRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpielzugRepositoryBruecke implements SpielzugRepository {
    @Override
    public void dokumentiere(Spielzug spielzug) {
        /*String pathString = null;

        try {
            Process p = Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            InputStream in = p.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            pathString = new String(b);
            pathString = pathString.split("\\s\\s+")[4];

        } catch (Throwable t) {
            t.printStackTrace();
        }

        pathString += "\\SchachLogs\\";

        try {

            Path path = Paths.get(pathString);

            //java.nio.file.Files;
            Files.createDirectories(path);

            System.out.println("Directory is created!");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
        String fileNameString = pathString + schachspiel.getSpielID() + ".txt";
        System.out.println(fileNameString);
        try {
            File logFile = new File(fileNameString);
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Path p = Path.of(fileNameString);
        StringBuilder text = new StringBuilder();
        for (String zug : schachspiel.getZuege()) {
            int index = schachspiel.getZuege().indexOf(zug);
            if (index % 2 == 0) {
                int spielzug = index / 2 + 1;
                text.append(spielzug).append(":");
            }
            text.append(zug).append(";");
            if (index % 2 == 1) {
                text.append("\n");
            }
        }
        try {

            Path filePath = Files.writeString(p, text.toString());
            String s = Files.readString(filePath);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

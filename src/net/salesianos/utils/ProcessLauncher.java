package net.salesianos.utils;

import java.io.File;
import java.io.IOException;

public class ProcessLauncher {

    public static Process startProccess(String route, String data, String output, String errorRoute)
            throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("java",
                route, data, output);
        processBuilder.redirectOutput(new File(output));
        processBuilder.redirectError(new File(errorRoute));
        return processBuilder.start();
    }
}

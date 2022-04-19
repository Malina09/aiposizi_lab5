package com.aposisi.lab1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String HELP = "\n-h --help\tto print info\n -p --port\tto set custom port\n";
    private static final int DEFAULT_PORT = 8082;

    public static void main(String... args) {
        int port = 8082;
        List<String> commands = Arrays.stream(args)
                .filter(x -> x.startsWith("-") | x.startsWith("--"))
                .collect(Collectors.toList());
        if (!commands.isEmpty()){
            Optional<String> helpCommandOptional = commands.stream()
                    .filter(x -> x.startsWith("-h") || x.startsWith("--help"))
                    .findFirst();
            if (helpCommandOptional.isPresent()){
                logger.log(Level.INFO, HELP);
                return;
            }
            Optional<String> portCommandOptional = commands.stream()
                    .filter(x -> x.startsWith("-p") || x.startsWith("--port"))
                    .findFirst();
            if (portCommandOptional.isPresent()){
                String portCommand = portCommandOptional.get();
                try {
                    String portString = portCommand.substring(portCommand.indexOf("=")+1);
                    port = Integer.parseInt(portString);
                    logger.log(Level.INFO, "ok, using port: " + port);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    logger.log(Level.WARN, "argument is invalid, using default(8082) port;");
                }
            }
        }

        try {
            ServerSocket serverConnect = new ServerSocket(port);
            logger.log(Level.INFO, "Server started. Listening for connections on port : " + port);

            while (true) {
                HttpServer myServer = new HttpServer(serverConnect.accept());

                System.out.println("Connecton opened. (" + new Date() + ")");

                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }


}

package ro.deiutzblaxo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ro.deiutzblaxo.user.UserService;

import java.io.IOException;

public class GRPCServer {

    public static void main(String[] args) {
        try {

            System.out.println("Server is starting...");
        Server server = ServerBuilder.forPort(1234).addService(new UserService()).build();

            server.start();

            System.out.println("Server is started");

            server.awaitTermination();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

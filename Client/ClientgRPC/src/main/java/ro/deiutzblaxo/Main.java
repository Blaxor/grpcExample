package ro.deiutzblaxo;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ro.deiutzblaxo.grpc.User;
import ro.deiutzblaxo.grpc.userGrpc;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",1234).usePlaintext().build();
        callSyncgRPCMessage(managedChannel);
        managedChannel.shutdown();



    }

    public static void callSyncgRPCMessage(ManagedChannel managedChannel){

        System.out.println("Starting sending callSyncgRPCMessage");

        userGrpc.userBlockingStub stub = userGrpc.newBlockingStub(managedChannel);

        User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setEmail("Email").setPassword("PASSSSSSSS").build();
        System.out.println("Created the login request and sending the request");
        User.APIResponse apiResponse =  stub.login(loginRequest);

        System.out.println("Response is " + apiResponse);
    }

    public static void callAsyncgRPCMessage(ManagedChannel managedChannel){
        userGrpc.userFutureStub futureStub = userGrpc.newFutureStub(managedChannel);

        User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setEmail("Email").setPassword("PASSSSSSSS").build();

        ListenableFuture<User.APIResponse> userFutureStub =
                futureStub.withDeadline(Deadline.after(5, TimeUnit.SECONDS)).login(loginRequest);



        Futures.addCallback(userFutureStub, new FutureCallback<User.APIResponse>() {
            @Override
            public void onSuccess(@Nullable User.APIResponse apiResponse) {
                Logger.getLogger(Main.class.getName()).log(Level.INFO,"Success!" + apiResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Failure " + throwable.getMessage() );
            }
        });
    }




}
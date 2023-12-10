package ro.deiutzblaxo.user;

import io.grpc.stub.StreamObserver;
import ro.deiutzblaxo.grpc.User;
import ro.deiutzblaxo.grpc.userGrpc.userImplBase;

public class UserService extends userImplBase {

    @Override
    public void login(User.LoginRequest request, StreamObserver<User.APIResponse> responseObserver) {
         System.out.println("Login with login request " + request);

        User.APIResponse.Builder response = User.APIResponse.newBuilder();

        response.setResponseCode(21);
        response.setResponseMessage("Login succsess?!");
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();


    }

    @Override
    public void register(User.RegisterRequest request, StreamObserver<User.APIResponse> responseObserver) {

        System.out.println("Register with register request " + request);

        User.APIResponse.Builder response = User.APIResponse.newBuilder();

        response.setResponseCode(21);
        response.setResponseMessage("Register sucess?!");
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}

package io.grpc.grpcinterface;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.0)",
    comments = "Source: financedata.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GetDataGrpc {

  private GetDataGrpc() {}

  public static final String SERVICE_NAME = "financedata.GetData";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.grpcinterface.DataRequest,
      io.grpc.grpcinterface.DataResponse> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetData",
      requestType = io.grpc.grpcinterface.DataRequest.class,
      responseType = io.grpc.grpcinterface.DataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.grpcinterface.DataRequest,
      io.grpc.grpcinterface.DataResponse> getGetDataMethod() {
    io.grpc.MethodDescriptor<io.grpc.grpcinterface.DataRequest, io.grpc.grpcinterface.DataResponse> getGetDataMethod;
    if ((getGetDataMethod = GetDataGrpc.getGetDataMethod) == null) {
      synchronized (GetDataGrpc.class) {
        if ((getGetDataMethod = GetDataGrpc.getGetDataMethod) == null) {
          GetDataGrpc.getGetDataMethod = getGetDataMethod =
              io.grpc.MethodDescriptor.<io.grpc.grpcinterface.DataRequest, io.grpc.grpcinterface.DataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.grpcinterface.DataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.grpcinterface.DataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GetDataMethodDescriptorSupplier("GetData"))
              .build();
        }
      }
    }
    return getGetDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetDataStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataStub>() {
        @java.lang.Override
        public GetDataStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataStub(channel, callOptions);
        }
      };
    return GetDataStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetDataBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingStub>() {
        @java.lang.Override
        public GetDataBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataBlockingStub(channel, callOptions);
        }
      };
    return GetDataBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetDataFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataFutureStub>() {
        @java.lang.Override
        public GetDataFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataFutureStub(channel, callOptions);
        }
      };
    return GetDataFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GetDataImplBase implements io.grpc.BindableService {

    /**
     */
    public void getData(io.grpc.grpcinterface.DataRequest request,
        io.grpc.stub.StreamObserver<io.grpc.grpcinterface.DataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.grpc.grpcinterface.DataRequest,
                io.grpc.grpcinterface.DataResponse>(
                  this, METHODID_GET_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class GetDataStub extends io.grpc.stub.AbstractAsyncStub<GetDataStub> {
    private GetDataStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataStub(channel, callOptions);
    }

    /**
     */
    public void getData(io.grpc.grpcinterface.DataRequest request,
        io.grpc.stub.StreamObserver<io.grpc.grpcinterface.DataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GetDataBlockingStub extends io.grpc.stub.AbstractBlockingStub<GetDataBlockingStub> {
    private GetDataBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.grpcinterface.DataResponse getData(io.grpc.grpcinterface.DataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GetDataFutureStub extends io.grpc.stub.AbstractFutureStub<GetDataFutureStub> {
    private GetDataFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.grpcinterface.DataResponse> getData(
        io.grpc.grpcinterface.DataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GetDataImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GetDataImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DATA:
          serviceImpl.getData((io.grpc.grpcinterface.DataRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.grpcinterface.DataResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GetDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetDataBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.grpcinterface.GetDataProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetData");
    }
  }

  private static final class GetDataFileDescriptorSupplier
      extends GetDataBaseDescriptorSupplier {
    GetDataFileDescriptorSupplier() {}
  }

  private static final class GetDataMethodDescriptorSupplier
      extends GetDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GetDataMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GetDataGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetDataFileDescriptorSupplier())
              .addMethod(getGetDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: financedata.proto

package io.grpc.grpcinterface;

public final class GetDataProto {
  private GetDataProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_financedata_DataRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_financedata_DataRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_financedata_DataRequest_MapListDataEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_financedata_DataRequest_MapListDataEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_financedata_ListOfString_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_financedata_ListOfString_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_financedata_DataResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_financedata_DataResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_financedata_DataResponse_MapListDataEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_financedata_DataResponse_MapListDataEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021financedata.proto\022\013financedata\"\221\002\n\013Dat" +
      "aRequest\022\023\n\013requestData\030\001 \001(\t\022\021\n\tstartTi" +
      "me\030\002 \001(\t\022\017\n\007endTime\030\003 \001(\t\022\r\n\005notes\030\004 \001(\t" +
      "\022+\n\010listData\030\005 \001(\0132\031.financedata.ListOfS" +
      "tring\022>\n\013mapListData\030\006 \003(\0132).financedata" +
      ".DataRequest.MapListDataEntry\032M\n\020MapList" +
      "DataEntry\022\013\n\003key\030\001 \001(\t\022(\n\005value\030\002 \001(\0132\031." +
      "financedata.ListOfString:\0028\001\"\035\n\014ListOfSt" +
      "ring\022\r\n\005value\030\001 \003(\t\"\217\002\n\014DataResponse\022\017\n\007" +
      "message\030\001 \001(\t\022\021\n\tstartTime\030\002 \001(\t\022\017\n\007endT" +
      "ime\030\003 \001(\t\022\r\n\005notes\030\004 \001(\t\022+\n\010listData\030\005 \001" +
      "(\0132\031.financedata.ListOfString\022?\n\013mapList" +
      "Data\030\006 \003(\0132*.financedata.DataResponse.Ma" +
      "pListDataEntry\032M\n\020MapListDataEntry\022\013\n\003ke" +
      "y\030\001 \001(\t\022(\n\005value\030\002 \001(\0132\031.financedata.Lis" +
      "tOfString:\0028\0012K\n\007GetData\022@\n\007GetData\022\030.fi" +
      "nancedata.DataRequest\032\031.financedata.Data" +
      "Response\"\000B-\n\025io.grpc.grpcinterfaceB\014Get" +
      "DataProtoP\001\242\002\003HLWb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_financedata_DataRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_financedata_DataRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_financedata_DataRequest_descriptor,
        new java.lang.String[] { "RequestData", "StartTime", "EndTime", "Notes", "ListData", "MapListData", });
    internal_static_financedata_DataRequest_MapListDataEntry_descriptor =
      internal_static_financedata_DataRequest_descriptor.getNestedTypes().get(0);
    internal_static_financedata_DataRequest_MapListDataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_financedata_DataRequest_MapListDataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_financedata_ListOfString_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_financedata_ListOfString_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_financedata_ListOfString_descriptor,
        new java.lang.String[] { "Value", });
    internal_static_financedata_DataResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_financedata_DataResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_financedata_DataResponse_descriptor,
        new java.lang.String[] { "Message", "StartTime", "EndTime", "Notes", "ListData", "MapListData", });
    internal_static_financedata_DataResponse_MapListDataEntry_descriptor =
      internal_static_financedata_DataResponse_descriptor.getNestedTypes().get(0);
    internal_static_financedata_DataResponse_MapListDataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_financedata_DataResponse_MapListDataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

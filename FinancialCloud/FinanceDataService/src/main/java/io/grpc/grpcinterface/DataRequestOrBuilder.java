// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: financedata.proto

package io.grpc.grpcinterface;

public interface DataRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:financedata.DataRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string requestData = 1;</code>
   * @return The requestData.
   */
  java.lang.String getRequestData();
  /**
   * <code>string requestData = 1;</code>
   * @return The bytes for requestData.
   */
  com.google.protobuf.ByteString
      getRequestDataBytes();

  /**
   * <code>string startTime = 2;</code>
   * @return The startTime.
   */
  java.lang.String getStartTime();
  /**
   * <code>string startTime = 2;</code>
   * @return The bytes for startTime.
   */
  com.google.protobuf.ByteString
      getStartTimeBytes();

  /**
   * <code>string endTime = 3;</code>
   * @return The endTime.
   */
  java.lang.String getEndTime();
  /**
   * <code>string endTime = 3;</code>
   * @return The bytes for endTime.
   */
  com.google.protobuf.ByteString
      getEndTimeBytes();

  /**
   * <code>string notes = 4;</code>
   * @return The notes.
   */
  java.lang.String getNotes();
  /**
   * <code>string notes = 4;</code>
   * @return The bytes for notes.
   */
  com.google.protobuf.ByteString
      getNotesBytes();

  /**
   * <code>.financedata.ListOfString listData = 5;</code>
   * @return Whether the listData field is set.
   */
  boolean hasListData();
  /**
   * <code>.financedata.ListOfString listData = 5;</code>
   * @return The listData.
   */
  io.grpc.grpcinterface.ListOfString getListData();
  /**
   * <code>.financedata.ListOfString listData = 5;</code>
   */
  io.grpc.grpcinterface.ListOfStringOrBuilder getListDataOrBuilder();

  /**
   * <code>map&lt;string, .financedata.ListOfString&gt; mapListData = 6;</code>
   */
  int getMapListDataCount();
  /**
   * <code>map&lt;string, .financedata.ListOfString&gt; mapListData = 6;</code>
   */
  boolean containsMapListData(
      java.lang.String key);
  /**
   * Use {@link #getMapListDataMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, io.grpc.grpcinterface.ListOfString>
  getMapListData();
  /**
   * <code>map&lt;string, .financedata.ListOfString&gt; mapListData = 6;</code>
   */
  java.util.Map<java.lang.String, io.grpc.grpcinterface.ListOfString>
  getMapListDataMap();
  /**
   * <code>map&lt;string, .financedata.ListOfString&gt; mapListData = 6;</code>
   */
  /* nullable */
io.grpc.grpcinterface.ListOfString getMapListDataOrDefault(
      java.lang.String key,
      /* nullable */
io.grpc.grpcinterface.ListOfString defaultValue);
  /**
   * <code>map&lt;string, .financedata.ListOfString&gt; mapListData = 6;</code>
   */
  io.grpc.grpcinterface.ListOfString getMapListDataOrThrow(
      java.lang.String key);
}
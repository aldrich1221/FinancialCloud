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
}
package com.zh.protocol.command;

/**
 * @Author zh2683
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte HEARTBEAT_REQUEST = 5;

    Byte HEARTBEAT_RESPONSE = 6;
}

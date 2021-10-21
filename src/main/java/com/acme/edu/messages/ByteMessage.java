package com.acme.edu.messages;

import com.acme.edu.Printer;

public class ByteMessage implements Message {
    private int messageValue;
    private static String messagePrefix = "primitive: ";
    private static int bufferSum;

    public ByteMessage(int message) {
        this.messageValue = message;
    }
    @Override
    public boolean isSameType(Message message){
        boolean isSameType = message instanceof ByteMessage;
        return isSameType;
    }

    @Override
    public Message accumulate(Message message) {
        if ((Long.valueOf(bufferSum) + Long.valueOf(((ByteMessage)message).messageValue)) < Byte.MAX_VALUE) {
            bufferSum += ((ByteMessage)message).messageValue;
            return message;
        } else {
            Printer.print(Integer.toString(Byte.MAX_VALUE));
            bufferSum = ((ByteMessage)message).messageValue - (Byte.MAX_VALUE - bufferSum);
            return new ByteMessage(bufferSum);
        }
    }

    @Override
    public String getBody() {
        return messagePrefix + messageValue;
    }

    @Override
    public void flush(){
        Printer.print(messagePrefix + bufferSum);
        bufferSum = 0;
    }

    @Override
    public void  init(){
        bufferSum+=messageValue;
    }
}


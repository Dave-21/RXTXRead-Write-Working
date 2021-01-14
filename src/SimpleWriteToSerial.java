import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.CommPort;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SimpleWriteToSerial {
    public OutputStream out;

    void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.printf("Error opening serial port '%s'. (Port busy)", portName);
        } else {
            int timeout = 200;
            CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort)commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

                out = serialPort.getOutputStream();
            } else {
                System.out.println("Sorry: Serial Ports only");
            }
        }
    }

    public static void writeToSerial(OutputStream out, String dataToSend) throws IOException {
        out.write(dataToSend.getBytes());
    }

    public static void main(String[] args) {
        try {
            SimpleWriteToSerial com = new SimpleWriteToSerial();
            com.connect("COM7");
            Scanner keyboard = new Scanner(System.in);
            int writeValue = 0;
            while (writeValue != 420) {
                System.out.println("enter an integer.. (enter 420 to exit.:");
                writeValue = keyboard.nextInt();
                writeToSerial(com.out, String.valueOf(writeValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

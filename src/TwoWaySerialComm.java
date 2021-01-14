import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class TwoWaySerialComm {

    public OutputStream out;
    public InputStream in;

    void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier
                .getPortIdentifier(portName);
        if( portIdentifier.isCurrentlyOwned() ) {
            System.out.println( "Error: Port is currently in use" );
        } else {
            int timeout = 2000;
            CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);

            if( commPort instanceof SerialPort ) {
                SerialPort serialPort = ( SerialPort )commPort;
                serialPort.setSerialPortParams( 9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE );
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();

                //( new Thread( new SerialReader( in ) ) ).start();
                //new SerialWriter( out, 180);
                System.out.println(in.read());
                //Scanner keyboard = new Scanner(System.in);
                //System.out.println("enter an integer.. (enter 420 to exit.:");
                //int degreesCount = 0;
                //while (degreesCount != 420) {
                    //degreesCount = keyboard.nextInt();
                    //( new Thread( new SerialWriter( out, degreesCount) ) ).start();
                //}
                //this.out.write(degreesCount);

                System.out.println("Ok. I'm pretty sure we're connected"); //that's what she said to me "-15/12/2020"
            } else {
                System.out.println( "Error: Only serial ports are handled by this example." );
            }
        }
    }

    public static class SerialReader{

        InputStream in;

        public SerialReader( InputStream in ) {
            this.in = in;
            run();
        }

        public void run() {
            byte[] buffer = new byte[ 1024 ];
            int len = -1;
            try {
                while((len = this.in.read(buffer)) > -1) {
                    String serialData = new String(buffer, 0, len);
                    System.out.print(serialData);
                    if (serialData.equals("stop")) {
                        System.out.println("SerialReading stopped");
                    }
                    //System.out.println("We're most likely reading Serial data");
                }
            }   catch(Exception e) {
                System.out.println("Failed at SerialReader");
            }
        }/* catch( IOException e ) {
                e.printStackTrace();
                System.out.println("Failed at SerialReader");
            }*/

    }

    public static class SerialWriter {

        OutputStream out;
        InputStream in;

        public SerialWriter(OutputStream out, InputStream in) {
            System.out.println("Just entered public SerialWriter method");
            this.out = out;
            this.in = in;

            run();
        }

        public void run() {
            try {
                System.out.println("Just started try method");
                int c = 0;
                // if connected SerialPort is sending data, c will == 0. If it's not, c will == -1
                while( (c = this.in.read()) > -1 ) {  // TODO: Get rid of System in "System.in.read()"
                    System.out.println("Printing: " + c);
                    this.out.write(c);
                }
            } catch( IOException e ) {
                e.printStackTrace();
                System.out.println("Failed at SerialWriter");
            }
        }
    }

    public static void main( String[] args ) {
        try {
            TwoWaySerialComm com = new TwoWaySerialComm();
            com.connect( "COM7" );
            //System.out.println(com.out);
            new SerialWriter(com.out, com.in);
        } catch( Exception e ) {
            //e.printStackTrace();
            e.printStackTrace();
            System.out.println("Failed at public main");
        }
    }

}


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Sandeep Thota 
 * This is the executable class file and provides access to commands needed to perform the required operations.
 * @author Sandeep Thota
 */
public class bbst {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Proper usage: java bbst test_100.txt < commands.txt > out_100.txt");
            System.exit(0);
        } else {
            ArrayList<Node> eventArrayList = null;
            AVLTreeEventCounter avlTreeEventCounter = null;
            BufferedReader fileReader = null;
            try {
                fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
                int lineCount = 0;
                StringTokenizer tokenizer;
                String scannedLine;
                while((scannedLine = fileReader.readLine()) != null) {
                    tokenizer = new StringTokenizer(scannedLine);
                    if (lineCount > 0) {
                        int ID = Integer.parseInt(tokenizer.nextToken());
                        int count = Integer.parseInt(tokenizer.nextToken());
                        Node event = new Node(ID,count);
                        eventArrayList.add(event);
                    } else {
                        int size = Integer.parseInt(tokenizer.nextToken());
                        eventArrayList = new ArrayList<Node>(size);
                    }
                    lineCount++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("File " + args[0] + " not found!");
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            finally {
                if(fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            if(eventArrayList != null) {
                avlTreeEventCounter = new AVLTreeEventCounter();
                avlTreeEventCounter.buildEventCounter(eventArrayList);
            }

            if(avlTreeEventCounter != null) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    String scannedString = scanner.nextLine();
                    Scanner stringScanner = new Scanner(scannedString);
                    while (stringScanner.hasNext()) {
                        String command = stringScanner.next();
                        if("increase".equalsIgnoreCase(command)) {
                            int ID = stringScanner.nextInt();
                            int amount = stringScanner.nextInt();
                            System.out.println("increase"+" "+ID+" "+amount);
                            System.out.println(avlTreeEventCounter.increase(ID, amount));
                        } else if("reduce".equalsIgnoreCase(command)) {
                            int ID = stringScanner.nextInt();
                            int amount = stringScanner.nextInt();
                            System.out.println("reduce"+" "+ID+" "+amount);
                            System.out.println(avlTreeEventCounter.reduce(ID, amount));
                        } else if("count".equalsIgnoreCase(command)) {
                            int ID = stringScanner.nextInt();
                            System.out.println("count"+" "+ID);
                            System.out.println(avlTreeEventCounter.count(ID));
                        } else if("inrange".equalsIgnoreCase(command)) {
                            int ID1 = stringScanner.nextInt();
                            int ID2 = stringScanner.nextInt();
                            System.out.println("inrange"+" "+ID1+" "+ID2);
                            ArrayList<Node> nodesInRange = avlTreeEventCounter.inRange(ID1, ID2);
                            for (Node node : nodesInRange) {
                            	System.out.print(node.count + " ");
							}
                            System.out.println("");
                        } else if("next".equalsIgnoreCase(command)) {
                            int ID = stringScanner.nextInt();
                            Node nextEvent = avlTreeEventCounter.next(ID);
                            System.out.println("next"+" "+ID);
                            System.out.println(((nextEvent != null) ? nextEvent.ID : 0) + " "
                                    + ((nextEvent != null) ? nextEvent.count : 0));
                        } else if("previous".equalsIgnoreCase(command)) {
                            int ID = stringScanner.nextInt();
                            Node previousEvent = avlTreeEventCounter.previous(ID);
                            System.out.println("previous"+" "+ID);
                            System.out.println(((previousEvent != null) ? previousEvent.ID : 0) + " "
                                    + ((previousEvent != null) ? previousEvent.count : 0));
                        } else if("quit".equalsIgnoreCase(command)) {
                        	System.out.println("quit");
                            stringScanner.close();
                            scanner.close();
                            System.exit(0);
                        } else {
                            System.out.println("Wrong input command!");
                        }
                    }
                    stringScanner.close();
                }
                scanner.close();
            }
        }
    }
}

/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Camp Badger
// Files: Camper.java, CamperBST.java, CampManager.java, CampTreeNode.java, CampEnrollmentApp.java
// This File: CampEnrollmentApp.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * Class utilizes the CampManager class to perform camp management tasks loaded in from a file
 * 
 * @author Ben Tarmann
 */
public class CampEnrollmentApp {
  /**
   * Main method uses an instance of CampManager to execute certain commands as read from a text
   * file
   * 
   * @param args
   * @throws IOException if file can not be found
   */
  public static void main(String[] args) throws IOException {
    CampManager management = new CampManager();
    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
    String[] splitInstruction;

    for (int i = 0; i < fileLines.size(); i++) {
      splitInstruction = fileLines.get(i).split(" "); // Parses line in file to retrieve instruction

      // Print statistics
      if (splitInstruction[0].trim().equals("S")) {
        management.printStatistics();
      }
      // Enroll
      else if (splitInstruction[0].trim().equals("E")) { // Enroll
        try {
          Camper newCamper = new Camper(splitInstruction[2], splitInstruction[1],
              Integer.parseInt(splitInstruction[3]));
          management.enrollCamper(newCamper);
          System.out.println(
              "Enrollment of " + splitInstruction[2] + " " + splitInstruction[1] + " Successful!");
        } catch (IllegalArgumentException e) { // camper too old to be enrolled
          System.out.println(e.getMessage());
        }
      }
      // Unenroll
      else if (splitInstruction[0].trim().equals("R")) {
        try {
          Camper delCamper = new Camper(splitInstruction[2], splitInstruction[1], 9);
          management.unenrollCamper(delCamper);
          System.out.println("Unenrollment of " + splitInstruction[2] + " " + splitInstruction[1]
              + " Successful!");
        } catch (NoSuchElementException e) { // camper to be deleted could not be found
          System.out.println(e.getMessage());
        }
      }
      // Traverse
      else if (splitInstruction[0].trim().equals("T")) {
        String order = splitInstruction[1];
        Iterator<Camper> traversal = management.traverse(order);
        System.out.println("--- " + order + " Traversal ---");
        while (traversal.hasNext()) {
          System.out.println(traversal.next().toString());
        }
        System.out.println("-------------------------");
      }
    }
  }
}

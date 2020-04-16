/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Camp Badger
// Files: Camper.java, CamperBST.java, CampManager.java, CampTreeNode.java, CampEnrollmentApp.java
// This File: CampManager.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a Camp Manager application for performing basic tasks in regards to
 * managing Camp Badger
 * 
 * @author Ben Tarmann
 */
public class CampManager {
  private CamperBST campers;
  private final static String[] CABIN_NAMES =
      new String[] {"Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"};

  /**
   * Constructor for the CampManager by initializing the campers field
   */
  public CampManager() {
    campers = new CamperBST();
  }

  /**
   * Prints statistics based on the current "state" of the camp. The statistics to be printed is the
   * total number of campers
   */
  public void printStatistics() {
    String statistics = "--- Camp Statistics ---\nNumber of Campers: " + campers.size()
        + "\n-----------------------\n";
    System.out.print(statistics);
  }

  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree
   * 
   * @param newCamper
   */
  public void enrollCamper(Camper newCamper) {
    if (newCamper.getAge() == 8 || newCamper.getAge() == 9) {
      newCamper.assignCabin(CABIN_NAMES[0]);
    } else if (newCamper.getAge() == 10 || newCamper.getAge() == 11 || newCamper.getAge() == 12) {
      newCamper.assignCabin(CABIN_NAMES[1]);
    } else if (newCamper.getAge() == 13 || newCamper.getAge() == 14) {
      newCamper.assignCabin(CABIN_NAMES[2]);
    }

    campers.insert(newCamper);
  }

  /**
   * "Unenrolls" a camper by removing them from the tree
   * 
   * @param delCamper the camper to be unenrolled from the camp
   * @throws NoSuchElementException if CamperBST.delete throws the exception
   */
  public void unenrollCamper(Camper delCamper) throws NoSuchElementException {
    campers.delete(delCamper);
  }

  /**
   * Traverses the tree in the designated order by calling it through the CamperBST class
   *  
   * @param order the type of traversal for the tree to perform
   * @return the Iterator of Campers from CamperBST.traverse()
   */
  public Iterator<Camper> traverse(String order) {
    return campers.traverse(order);
  }
}

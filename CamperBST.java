/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Camp Badger
// Files: Camper.java, CamperBST.java, CampManager.java, CampTreeNode.java, CampEnrollmentApp.java
// This File: CamperBST.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class to represent a binary search tree of campers
 * 
 * @author Benjamin Tarmann
 */
public class CamperBST {
  public CampTreeNode root;
  private int size;
  private LinkedList<Camper> traversedLList; // LinkedList to maintain current traversal

  /**
   * Constructor creates a CamperBST instance with a size of 0
   */
  public CamperBST() {
    size = 0;
  }

  /**
   * Returns the current size of the CamperBST
   * 
   * @return size of this CamperBST
   */
  public int size() {
    return size;
  }

  /**
   * Returns true if the tree is empty, false otherwise
   * 
   * @return true if the tree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (root == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Starts tree insertion by calling insertHelp() on the root and assigning root to be the subtree
   * returned by that method
   * 
   * @param newCamper new camper to be inserted into the BST
   */
  public void insert(Camper newCamper) {
    root = insertHelp(root, newCamper);
    size++;
  }

  /**
   * Recursive helper method to insert
   * 
   * @param current   the "root" of the subtree we are inserting into (i.e. the node we are
   *                  currently at)
   * @param newCamper the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
    if (current == null) {
      return new CampTreeNode(newCamper, null, null);
    } else if (newCamper.compareTo(current.getData()) < 0) {
      // if there is no left subtree, insert new node there now
      if (current.getLeftNode() == null) { // case if root is null
        current.setLeftNode(new CampTreeNode(newCamper, null, null));
      }
      // else move on to the next left node
      else {
        insertHelp(current.getLeftNode(), newCamper);
      }
    } else if (newCamper.compareTo(current.getData()) > 0) {
      // if there is no right subtree, insert a new node there now
      if (current.getRightNode() == null) {
        current.setRightNode(new CampTreeNode(newCamper, null, null));
      }
      // else move on to next right node
      else {
        insertHelp(current.getRightNode(), newCamper);
      }
    }

    return current;
  }

  /**
   * Deletes a Camper in the BST if it exists
   * 
   * @param key the camper to be deleted from the tree
   * @throws NoSuchElementException if it is thrown by deleteHelp
   */
  public void delete(Camper key) throws NoSuchElementException {
    root = deleteHelp(root, key);
    size--;
  }

  /**
  * Recursive helper method to delete.
  * 
  * @param current the "root" of the subtree we are deleting from (i.e. the node we are currently
  *                at)
  * @param key     the camper to be deleted from the tree
  * @return the root of the modified subtree we delete from
  * @throws NoSuchElementException if the camper is not in the tree
  */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {
    // current is only equal to null if the key does not exist in the tree
    if (current == null) {
      throw new NoSuchElementException("That camper is not enrolled.");
    }
    
    // current is the node to be removed
    if (key.compareTo(current.getData()) == 0) { 
      // if deleting leaf node
      if (current.getLeftNode() == null && current.getRightNode() == null) {
        return null;
      }
      // if deleting internal node with one child (right)
      else if (current.getLeftNode() == null) {
        return current.getRightNode();
      }
      // if deleting internal node with one child (left)
      else if (current.getRightNode() == null) {
        return current.getLeftNode();
      }
      // if deleting internal node with two children
      else {
        CampTreeNode successor = successorHelp(current);
        current.setData(successor.getData());
        current.setRightNode(deleteHelp(current.getRightNode(), successor.getData()));
        return current; 
      }
    }
    // move to left child
    else if (key.compareTo(current.getData()) < 0) {
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
      return current;
    }
    // move to right child
    else {
      current.setRightNode(deleteHelp(current.getRightNode(), key));
      return current; 
    }
  }
  
  /**
   * Helper method to find the successor of a node with two children that is to be removed
   * 
   * @param current node to find the successor for
   * @return successor for designated node passed in as parameter
   */
  private CampTreeNode successorHelp(CampTreeNode current) {
    CampTreeNode suc = current.getRightNode();
    while (suc.getLeftNode() != null) {
      suc = suc.getLeftNode();
    }
    // delete successor from tree
    return suc;

  }

  /**
   * Returns an iterator of camper in the correct order as designated
   * 
   * @param order the designated order to traverse the tree
   * @return an iterator of camper in the correct order as designated
   */
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if (traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
      // clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Recursive helper method to traverse. Will take current CampTreeNode's data and add it to
   * traversedLList based on given order. Then continue to recurse on the correct subtree
   * 
   * @param current the root of the current subtree we are traversing
   * @param order the type of traversal to perform
   */
  private void traverseHelp(CampTreeNode current, String order) {
    if (order.equals("PREORDER")) {
      if (current == null) {
        return;
      }
      traversedLList.add(current.getData());
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);
      
    }
    else if (order.equals("POSTORDER")) {
      if (current == null) {
        return;
      }
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);
      traversedLList.add(current.getData());
    }
    else if (order.equals("INORDER")) {
      if (current == null) {
        return;
      }
      traverseHelp(current.getLeftNode(), order);
      traversedLList.add(current.getData());
      traverseHelp(current.getRightNode(), order);
    }
  }

  /**
   * Prints the contents of this tree in alphabetical order
   */
  public void print() {
    printHelp(root);
  }

  /**
   * Helper method for the print() method
   * 
   * @param current node to be printed
   */
  private void printHelp(CampTreeNode current) {
    if (current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }
}

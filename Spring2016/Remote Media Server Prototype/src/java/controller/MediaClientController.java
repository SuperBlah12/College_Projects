package ser321.media;

import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.beans.*;
import java.net.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.Runtime;
import tjcole2.media.MediaDescription;

/**
 * Copyright (c) 2015 Tim Lindquist,
 * Software Engineering,
 * Arizona State University at the Polytechnic campus
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2
 * of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but without any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html
 * see also: https://www.gnu.org/licenses/gpl-faq.html
 * so you are aware of the terms and your rights with regard to this software.
 * Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: This class provides a simple controller demonstrating the use
 * of the MediaLibraryGui class. It is intended to be used in creating student
 * solutions to ser321 media playback and browser assignments.
 * This problem provides for browsing and managing information about
 * media files. It uses a Swing JTree, and JTextField controls to 
 * realize a GUI with a split pane. The left pane contains an expandable
 * JTree of the media library.
 * This file provides the controler for the client.
 *
 * This software is meant to run on Debian Wheezy Linux
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering,
 *                       IAFSE, ASU at the Polytechnic campus
 * @date    July, 2015
 **/
public class MediaClientController extends MediaLibraryGui implements
                                                       TreeWillExpandListener,
     					               ActionListener,
					               TreeSelectionListener {

   private static final boolean debugOn = false;
   private static final boolean bootstrapOn = false;
   private static MediaClient mc;
   private boolean stopPlaying;         //shared with playing thread.
   private File song = new File("/DataClient/Song.mp3");
   private File video = new File("/DataClient/Video.mp4");


   public MediaClientController(String author, String ipAdd, String jPort, String dPort) {
      super(author);
      mc = new MediaClient(ipAdd, jPort, dPort);
      stopPlaying = false;
      if(bootstrapOn){
         System.out.println("bootstraping a single media description ...");
      }
      song.deleteOnExit();
      video.deleteOnExit();
      for(int i=0; i<userMenuItems.length; i++){
         for(int j=0; j<userMenuItems[i].length; j++){
            userMenuItems[i][j].addActionListener(this);
         }
      }
      //tree.addTreeWillExpandListener(this);
      try{
         tree.addTreeSelectionListener(this);
         rebuildTree();
      }catch (Exception ex){
         JOptionPane.showMessageDialog(this,"Handling "+
                                " constructor exception: " + ex.getMessage());
      }
      setVisible(true);
   }

   private void debug(String message) {
      if (debugOn)
         System.out.println("debug: "+message);
   }

   /**
    * create and initialize nodes in the JTree of the left pane.
    * buildInitialTree is called by MediaLibraryGui to initialize the JTree.
    * Classes that extend MediaLibraryGui should override this method to 
    * perform initialization actions specific to the extended class.
    * The default functionality is to set base as the label of root.
    * In your solution, you will probably want to initialize by deserializing
    * your library and displaying the categories and subcategories in the
    * tree.
    * @param root Is the root node of the tree to be initialized.
    * @param base Is the string that is the root node of the tree.
    */
   public void buildInitialTree(DefaultMutableTreeNode root, String base){
      //set up the context and base name
      try{
         root.setUserObject(base);
      }catch (Exception ex){
         JOptionPane.showMessageDialog(this,"exception initial tree:"+ex);
         ex.printStackTrace();
      }
   }

   public void rebuildTree(){
      String[] musicList = mc.getMusic();
      java.util.List<String> aList = new ArrayList<String>();
      for (int i = 0; i < musicList.length; i++) {
      		aList.add(mc.getClip(musicList[i]).getAlbum());
      }
      String[] videoList = mc.getVideos();
      java.util.List<String> gList = new ArrayList<String>();
      for (int i = 0; i < videoList.length; i++) {
      		gList.add(mc.getClip(videoList[i]).getGenre());
      }
      String[] musicAlbum = new String[aList.size()];
      aList.toArray(musicAlbum);
      String[] videoGenre = new String[gList.size()];
      gList.toArray(videoGenre);
      tree.removeTreeSelectionListener(this);
      DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
      clearTree(root, model);
      DefaultMutableTreeNode musicNode = new DefaultMutableTreeNode("Music");
      model.insertNodeInto(musicNode, root, model.getChildCount(root));
      DefaultMutableTreeNode videoNode = new DefaultMutableTreeNode("Video");
      model.insertNodeInto(videoNode, root, model.getChildCount(root));
      // put nodes in the tree for all  registered with the library
      for (int i = 0; i<musicList.length; i++){
         String aMTitle = musicList[i];
         String anAlbum = musicAlbum[i];
         DefaultMutableTreeNode toAdd = new DefaultMutableTreeNode(aMTitle);
         DefaultMutableTreeNode subNode = getSubLabelled(musicNode,anAlbum);
         if(subNode!=null){ // if album subnode already exists
            debug("album exists: "+anAlbum);
            model.insertNodeInto(toAdd, subNode,
                                 model.getChildCount(subNode));
         }else{ // album node does not exist
            DefaultMutableTreeNode anAlbumNode =
               new DefaultMutableTreeNode(anAlbum);
            model.insertNodeInto(anAlbumNode, musicNode,
                                 model.getChildCount(musicNode));
            DefaultMutableTreeNode aSubCatNode = 
               new DefaultMutableTreeNode("aSubCat");
            debug("adding subcat labelled: "+"aSubCat");
            model.insertNodeInto(toAdd,anAlbumNode,
                                 model.getChildCount(anAlbumNode));
         }
      }
      // put nodes in the tree for all video registered with the library
      for (int i = 0; i<videoList.length; i++){
         String aTitle = videoList[i];
         String aGenre = videoGenre[i];
         DefaultMutableTreeNode toAdd = new DefaultMutableTreeNode(aTitle);
         DefaultMutableTreeNode subNode = getSubLabelled(videoNode,aGenre);
         if(subNode!=null){ // if album subnode already exists
            model.insertNodeInto(toAdd, subNode, model.getChildCount(subNode));
         }else{ // album node does not exist
            DefaultMutableTreeNode anAlbumNode =
               new DefaultMutableTreeNode(aGenre);
            model.insertNodeInto(anAlbumNode, videoNode,
                                 model.getChildCount(videoNode));
            DefaultMutableTreeNode aSubCatNode = 
               new DefaultMutableTreeNode("aSubCat");
            debug("adding subcat labelled: "+"aSubCat");
            model.insertNodeInto(toAdd,anAlbumNode,
                                 model.getChildCount(anAlbumNode));
         }
      }
      // expand all the nodes in the JTree
      for(int r =0; r < tree.getRowCount(); r++){
         tree.expandRow(r);
      }
      tree.addTreeSelectionListener(this);
   }

   private void clearTree(DefaultMutableTreeNode root, DefaultTreeModel model){
      try{
         DefaultMutableTreeNode next = null;
         int subs = model.getChildCount(root);
         for(int k=subs-1; k>=0; k--){
            next = (DefaultMutableTreeNode)model.getChild(root,k);
            debug("removing node labelled:"+(String)next.getUserObject());
            model.removeNodeFromParent(next);
         }
      }catch (Exception ex) {
         System.out.println("Exception while trying to clear tree:");
         ex.printStackTrace();
      }
   }

   private DefaultMutableTreeNode getSubLabelled(DefaultMutableTreeNode root,
                                                 String label){
      DefaultMutableTreeNode ret = null;
      DefaultMutableTreeNode next = null;
      boolean found = false;
      for(Enumeration e = root.children(); e.hasMoreElements();){
         next = (DefaultMutableTreeNode)e.nextElement();
         debug("sub with label: "+(String)next.getUserObject());
         if (((String)next.getUserObject()).equals(label)){
            debug("found sub with label: "+label);
            found = true;
            break;
         }
      }
      if(found)
         ret = next;
      else
         ret = null;
      return ret;
   }

   public void treeWillCollapse(TreeExpansionEvent tee) {
      debug("In treeWillCollapse with path: "+tee.getPath());
      tree.setSelectionPath(tee.getPath());
   }

   public void treeWillExpand(TreeExpansionEvent tee) {
      debug("In treeWillExpand with path: "+tee.getPath());
      //DefaultMutableTreeNode dmtn =
      //    (DefaultMutableTreeNode)tee.getPath().getLastPathComponent();
      //System.out.println("will expand node: "+dmtn.getUserObject()+
      //		   " whose path is: "+tee.getPath());
   }

   public void valueChanged(TreeSelectionEvent e) {
      try{
         tree.removeTreeSelectionListener(this);
         DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
         if(node!=null){
            String nodeLabel = (String)node.getUserObject();
            debug("In valueChanged. Selected node labelled: "+nodeLabel);
            // is this a terminal node?
            if(node.getChildCount()==0 &&
               (node != (DefaultMutableTreeNode)tree.getModel().getRoot())){
               MediaDescription sel = mc.getClip(nodeLabel);
 		titleJTF.setText(sel.getTitle());
		authorJTF.setText(sel.getAuthor());
		if(sel.getMediaType() == 0)
			albumJTF.setText(sel.getAlbum());
		else
			albumJTF.setText("");
		genreJTF.setText(sel.getGenre());
		fileNameJTF.setText(sel.getFilename());
               	typeJCB.setSelectedIndex(sel.getMediaType());
            }
         }
      }catch (Exception ex){
         ex.printStackTrace();
      }
      tree.addTreeSelectionListener(this);
   }

   public void actionPerformed(ActionEvent e) {
      tree.removeTreeSelectionListener(this);
      if(e.getActionCommand().equals("Exit")) {
        	mc.saveLibrary(); 
		System.exit(0);
      }else if(e.getActionCommand().equals("Restore")) {
         rebuildTree();
         System.out.println("Restore "+((true)?"successful":"unsuccessful"));
      }else if(e.getActionCommand().equals("Tree Refresh")) {
         rebuildTree();
      }else if(e.getActionCommand().equals("Play")){
         try{
            String fileType = ((typeJCB.getSelectedIndex()==0)?
                                ".mp3":
                                ".mp4");
            String nodeLabel = titleJTF.getText();
            String serverFile = fileNameJTF.getText();
            if(nodeLabel.equals("")) {
               nodeLabel = ((typeJCB.getSelectedIndex()==0)?
                                "Swan Song":
                                "Machu Picchu Time Lapse");
               serverFile = ((typeJCB.getSelectedIndex()==0)?
                                "PaperNavySwanSong.mp3":
                                "MachuPicchuTimelapseVimeo.mp4");
            }
            if(!serverFile.endsWith(fileType)){
               if(serverFile.endsWith(".mp4")){
                  typeJCB.setSelectedIndex(1);
               } else if (serverFile.endsWith(".mp3")){
                  typeJCB.setSelectedIndex(0);
               } else {
                  serverFile.concat(fileType);
               }
            }
            if(song.exists()) {
               song.delete();
            }
            if(video.exists()) {
               video.delete();
            }
            boolean fileExists = mc.downloadMedia(serverFile, typeJCB.getSelectedIndex());
            if(fileExists) {
               String aURIPath = "file://"+System.getProperty("user.dir")+
                  ((typeJCB.getSelectedIndex()==0)?"/DataClient/Song.mp3":
                   "/DataClient/Video.mp4");
               playMedia(aURIPath, nodeLabel);
            } else {
               JOptionPane.showMessageDialog(null,
                  "File does not exist on server.",
                  "Playback error",
                  JOptionPane.ERROR_MESSAGE);
            }
         }catch(Exception ex){
            System.out.println("Execption trying to play media:");
            ex.printStackTrace();
         }
      } else if(e.getActionCommand().equals("Add")) {
		String alb = "";
		if(typeJCB.getSelectedIndex() == 0)
			alb = albumJTF.getText();
		mc.addClip(new MediaDescription(typeJCB.getSelectedIndex(),
		titleJTF.getText(),
		authorJTF.getText(),
		alb,
		genreJTF.getText(),
		fileNameJTF.getText()));
		rebuildTree();
      } else if(e.getActionCommand().equals("Remove")) {
		mc.removeClip(titleJTF.getText());
		rebuildTree();
      } else if(e.getActionCommand().equals("Save")) {
        mc.saveLibrary();
      }
      tree.addTreeSelectionListener(this);
   }

   public boolean sezToStop(){
      return stopPlaying;
   }

   public static void main(String args[]) {
      try{
         String authorName = "DEFAULT Library";
         String ipAdd = "127.0.0.1";
	 String jPort = "8080";
	 String dPort = "3030";
	 if(args.length >= 1) {
            authorName = args[0] + "'s Library";
         }
	 if(args.length >= 2) {
	    ipAdd = args[1];
	 }
	 if(args.length >= 3) {
	    jPort = args[2];
	 }
	 if(args.length == 4) {
	    dPort = args[3];
	 }
	 MediaClientController mcc = new MediaClientController(authorName, ipAdd, jPort, dPort);
      }catch (Exception ex){
         System.out.println("Exception in main: "+ex.getMessage());
         ex.printStackTrace();
      }
   }
}

package com.catmktg.monitoring.StoreMonitor.ui;

import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.catmktg.monitoring.StoreMonitor.model.TouchPoint;

public class TouchPointTree extends JTree implements TreeSelectionListener {

	private DefaultMutableTreeNode root;

	public TouchPointTree(DefaultMutableTreeNode rootNode) {
		super(rootNode);
		root = rootNode;
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		putClientProperty("JTree.lineStyle", "Vertical");
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) {
			TouchPoint touchPoint = (TouchPoint) nodeInfo;
			System.out.println(touchPoint.touchPointId);
		}
	}

	/**
	 * Method adds a new retailer to the tree and adds all touch points under it.
	 * Note - If retailer node already exists then only unseen touch points are added.
	 * @param retailerId
	 * @param touchPointList
	 */
	public void addNewRetailer(String retailerId, List<TouchPoint> touchPointList) {
		DefaultMutableTreeNode retailerNode = searchNode(retailerId, root);
		boolean newRetailer = false;
		if(retailerNode == null) {
			retailerNode = new DefaultMutableTreeNode(retailerId);
			newRetailer = true;
			root.add(retailerNode);
		} 
		// Only add nodes which are not already in the tree.
		for(TouchPoint tp : touchPointList) {
			DefaultMutableTreeNode tpNode = null;
			if(!newRetailer) {
				tpNode = searchNode(tp.touchPointId, retailerNode);	
			}
			if(tpNode != null) continue; // If touchPoint node exist dont add continue
			tpNode = new DefaultMutableTreeNode(tp);
			retailerNode.add(tpNode);
		}
		
	}

	public DefaultMutableTreeNode searchNode(String nodeId, DefaultMutableTreeNode rootNode) {
		DefaultMutableTreeNode node  = null;
		Enumeration e = rootNode.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode temp = (DefaultMutableTreeNode) e.nextElement();
			if (nodeId.equals(temp.getUserObject().toString())) {
				node = temp;
				break;
			}
		}
		return node;
	}

	public void removeRetailer(String retailerId) {

	}

	public void removeTouchPoint(String retailerId, List<TouchPoint> touchPointList) {

	}
}

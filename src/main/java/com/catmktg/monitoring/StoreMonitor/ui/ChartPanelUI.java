package com.catmktg.monitoring.StoreMonitor.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;
import com.catmktg.monitoring.StoreMonitor.model.TouchPoint;

/**
 * Main panel that hosts the tree and chart panels.
 * 
 * @author amitvc
 *
 */
public class ChartPanelUI extends JPanel {
	private JEditorPane htmlPane;
	private TouchPointTree tree;

	public ChartPanelUI() {
		super(new GridLayout(1, 0));

		// Create the scroll pane and add the tree to it.
		tree = new TouchPointTree(new DefaultMutableTreeNode("Retailers"));
		JScrollPane treeView = new JScrollPane(tree);

		// Create the HTML viewing pane.
		htmlPane = new JEditorPane();
		htmlPane.setEditable(true);

		JScrollPane htmlView = new JScrollPane(htmlPane);

		// Add the scroll panes to a split pane.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(treeView);
		splitPane.setRightComponent(htmlView);
		splitPane.setAutoscrolls(true);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.3);

		Dimension minimumSize = new Dimension(300, 200);
		htmlView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		splitPane.setPreferredSize(new Dimension(1100, 1100));
		// Add the split pane to this panel.
		add(splitPane);
	}

	public void addTouchPoints(String retailerId, List<TouchPoint> touchPoints) {
		tree.addNewRetailer(retailerId, touchPoints);
	}
}
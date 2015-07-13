package opencv;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Result extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	public Result() {
		setBounds(100, 100, 450, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(null);
		
	    JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 434, 50);
		lblNewLabel.setIcon(new ImageIcon("image1.jpg"));
		contentPanel.add(lblNewLabel);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOK.setBounds(353, 118, 71, 26);
		contentPanel.add(btnOK);
	}
	public void disp_percen(int d){
		JLabel lblSimilarity = new JLabel("Similarity : "+d+" %");
		lblSimilarity.setBounds(10, 64, 100, 14);
		contentPanel.add(lblSimilarity);
		JProgressBar progressBar = new JProgressBar();
	    progressBar.setBounds(10, 83, 414, 24);
	    progressBar.setMinimum(0);
	    progressBar.getMaximum();
	    progressBar.setValue(d);
		contentPanel.add(progressBar);
	}
}

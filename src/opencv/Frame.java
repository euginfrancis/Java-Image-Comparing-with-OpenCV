package opencv;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import org.opencv.core.Core;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Frame {

	private JFrame frame;
	private JTextField img_url_1;
	private JTextField img_url_2;
	private CV c_v = new CV();
	
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() {
		initialize();
	}
    private BufferedImage image_1,image_2;
    private boolean image_1_load_status = false,image_2_load_status = false;
    
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 439, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(4, 100, 191, 202);
		panel.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JLabel img_1= new JLabel("No image to display");
		img_1.setBounds(6, 16, 180, 180);
		panel.add(img_1);
		img_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(239, 100, 191, 202);
		panel_1.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		final JLabel img_2 = new JLabel("No image to display");
		img_2.setBounds(6, 16, 180, 180);
		panel_1.add(img_2);
		img_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		img_url_1 = new JTextField();
		img_url_1.setBounds(10, 321, 104, 20);
		frame.getContentPane().add(img_url_1);
		img_url_1.setColumns(10);
		
		img_url_2 = new JTextField();
		img_url_2.setBounds(245, 321, 104, 20);
		frame.getContentPane().add(img_url_2);
		img_url_2.setColumns(10);
		
		JLabel lblUrlOfFirst = new JLabel("URL of first image");
		lblUrlOfFirst.setBounds(10, 306, 88, 14);
		lblUrlOfFirst.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(lblUrlOfFirst);
		
		JLabel lblUrlOfSecond = new JLabel("URL of second image");
		lblUrlOfSecond.setBounds(245, 306, 104, 14);
		lblUrlOfSecond.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(lblUrlOfSecond);
		
		JLabel lblNewLabel = new JLabel("label_3");
		lblNewLabel.setIcon(new ImageIcon("image.jpg"));
		lblNewLabel.setBounds(0, 0, 446, 89);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("image1.jpg"));
		lblNewLabel_1.setBounds(0, 384, 433, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(174, 350, 88, 23);
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(image_1_load_status && image_2_load_status)
				{	
					c_v.compare_image(image_1, image_2);
				}else{
					error_dia("Please load both images");
				}
			}
		});
		frame.getContentPane().add(btnCompare);
		
		JButton btnLoad_1 = new JButton("Load");
		btnLoad_1.setBounds(124, 318, 65, 23);
		btnLoad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					image_1 = ImageIO.read(new URL(img_url_1.getText()));
					image_1_load_status = true;
					image_1 = img_resize(image_1);
				    ImageIcon icon_1 = new ImageIcon(image_1);
					img_1.setIcon(icon_1);
				} catch (IOException e1) {
					error_dia("Please enter valid URL");
				}
			}
		});
		frame.getContentPane().add(btnLoad_1);
		
		JButton btnLoad_2 = new JButton("Load");
		btnLoad_2.setBounds(359, 320, 65, 23);
		btnLoad_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					image_2 = ImageIO.read(new URL(img_url_2.getText()));
					image_2_load_status = true;
				    image_2 = img_resize(image_2);
				    ImageIcon icon_2 = new ImageIcon(image_2);
					img_2.setIcon(icon_2);
				} catch (IOException e1) {
					error_dia("Please enter valid URL");
				}
			}
		});
		frame.getContentPane().add(btnLoad_2);
		
	}

	private BufferedImage img_resize(BufferedImage img_temp){
		BufferedImage dimg = new BufferedImage(180,180,img_temp.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img_temp,0,0,179,179,0,0,img_temp.getWidth()
				,img_temp.getHeight(),null);
		g.dispose();
		return dimg;
	}
	private void  error_dia(String error)
	{
		Error no_img = new Error();
		no_img.dis_error(error);
		no_img.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		no_img.setVisible(true);
	}
}

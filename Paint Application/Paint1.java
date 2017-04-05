import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Paint {
	public static void main(String[] args) {
		PaintWindow frame = new PaintWindow();
		frame.setTitle("Start Painting!");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}

class PaintWindow extends JFrame {
	private JPanel sidePanel;
	private DrawObjects drawArea;
	
	public PaintWindow() {
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(50, 100));
		
		drawArea = new DrawObjects();

		// Creates a new container to hold the different components
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(sidePanel, BorderLayout.EAST);
		content.add(drawArea, BorderLayout.CENTER);

		// Add color buttons
		createColorButton(Color.BLACK);
		createColorButton(Color.LIGHT_GRAY);
		createColorButton(Color.RED);
		createColorButton(Color.ORANGE);
		createColorButton(Color.YELLOW);
		createColorButton(Color.GREEN);
		createColorButton(Color.CYAN);
		createColorButton(Color.BLUE);
		createColorButton(Color.MAGENTA);
		createColorButton(Color.PINK);

		// Creates the clear button
		JButton clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(40, 20));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawArea.clear();
			}
		});
		sidePanel.add(clearButton);
	
	}

	public void createColorButton(final Color color) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(16, 16));
		
		//Changes background color of the button
		button.setBackground(color);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawArea.changeColor(color);
			}
		});
		
		sidePanel.add(button);
	}
}

class DrawObjects extends JComponent {
	Image image; 
	Graphics2D graphics2D; 	
	Point startPoint, endPoint;

	public DrawObjects() {
		init();
	}
	
	public void init() {
		setDoubleBuffered(false);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				startPoint = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				endPoint = e.getPoint();

				graphics2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
				repaint();

				startPoint.x = endPoint.x;
				startPoint.y = endPoint.y;
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON); //makes lines look more glowy

			clear();
		}

		g.drawImage(image, 0, 0, null);
	}

	public void changeColor(Color color) {
		graphics2D.setPaint(color);
		repaint();
	}
	
	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

}
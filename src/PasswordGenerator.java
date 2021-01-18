import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordGenerator implements ActionListener {

	final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600,
			  TEXT_X = 40, TEXT_Y = 50, TEXT_WIDTH = 700, TEXT_HEIGHT = 100,
			  UPPER_X = 100, UPPER_Y = 200, UPPER_WIDTH = 200, UPPER_HEIGHT = 50,
			  LOWER_X = 100, LOWER_Y = 275, LOWER_WIDTH = 200, LOWER_HEIGHT = 50,
			  NUMBERS_X = 100, NUMBERS_Y = 350, NUMBERS_WIDTH = 200, NUMBERS_HEIGHT = 50,
			  SYMBOLS_X = 100, SYMBOLS_Y = 425, SYMBOLS_WIDTH = 200, SYMBOLS_HEIGHT = 50,
			  LENGTH_X = 400, LENGTH_Y = 175, LENGTH_WIDTH = 300, LENGTH_HEIGHT = 100,
			  ADD_X = 400, ADD_Y = 275, ADD_WIDTH = 100, ADD_HEIGHT = 50,
			  SUB_X = 550, SUB_Y = 275, SUB_WIDTH = 100, SUB_HEIGHT = 50,
			  GEN_X = 400, GEN_Y = 375, GEN_WIDTH = 250, GEN_HEIGHT = 100;

	final Color BACKGROUND_COLOR = new Color(51, 102, 153),
				FOREGROUND_COLOR = new Color(102, 153, 153);

	final char[] UPPERS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'},
				 LOWERS = {'a','b','c','d','e','f','g','h','i','j','K','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
				 NUMS = {'0','1','2','3','4','5','6','7','8','9'},
				 SYMS = {'{','}','[',']','(',')','/','"','`','~',',','<','>'};

	final Font bigFont = new Font("Monospaced Bold", Font.BOLD, 75);
	final Font medFont = new Font("Monospaced Bold", Font.BOLD, 50);
	final Font smallFont = new Font("Monospaced Bold", Font.BOLD, 25);

	JFrame frame;
	ImageIcon frameIcon;
	JTextField text, length;
	JButton add, sub, generate;
	JCheckBox upperCase, lowerCase, numbers, symbols;

	int passLength = 12;
	String password = "    Click Generate";

	public static void main(String[] args) { new PasswordGenerator(); }

	PasswordGenerator() {
		frame = new JFrame("Password Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.getContentPane().setBackground(BACKGROUND_COLOR);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);

		frameIcon = new ImageIcon("C:\\Users\\avery\\Java\\MyPasswordGenerator\\P_ICON.PNG");

		text = new JTextField();
		text.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text.setEditable(false);
		text.setFont(bigFont);
		text.setBackground(FOREGROUND_COLOR);
		text.setBorder(null);
		text.setForeground(Color.BLACK);
		text.setText(password);

		upperCase = new JCheckBox("Upper Case");
		upperCase.setBounds(UPPER_X, UPPER_Y, UPPER_WIDTH, UPPER_HEIGHT);
		upperCase.setFont(smallFont);
		upperCase.setFocusable(false);
		upperCase.setBackground(BACKGROUND_COLOR);
		upperCase.setForeground(Color.BLACK);
		upperCase.setSelected(true);

		lowerCase = new JCheckBox("Lower Case");
		lowerCase.setBounds(LOWER_X, LOWER_Y, LOWER_WIDTH, LOWER_HEIGHT);
		lowerCase.setFont(smallFont);
		lowerCase.setFocusable(false);
		lowerCase.setBackground(BACKGROUND_COLOR);
		lowerCase.setForeground(Color.BLACK);
		lowerCase.setSelected(true);

		numbers = new JCheckBox("Numbers");
		numbers.setBounds(NUMBERS_X, NUMBERS_Y, NUMBERS_WIDTH, NUMBERS_HEIGHT);
		numbers.setFont(smallFont);
		numbers.setFocusable(false);
		numbers.setBackground(BACKGROUND_COLOR);
		numbers.setForeground(Color.BLACK);
		numbers.setSelected(true);

		symbols = new JCheckBox("Symbols");
		symbols.setBounds(SYMBOLS_X, SYMBOLS_Y, SYMBOLS_WIDTH, SYMBOLS_HEIGHT);
		symbols.setFont(smallFont);
		symbols.setFocusable(false);
		symbols.setBackground(BACKGROUND_COLOR);
		symbols.setForeground(Color.BLACK);
		symbols.setSelected(true);

		length = new JTextField();
		length.setBounds(LENGTH_X, LENGTH_Y, LENGTH_WIDTH, LENGTH_HEIGHT);
		length.setEditable(false);
		length.setFont(medFont);
		length.setBackground(BACKGROUND_COLOR);
		length.setBorder(null);
		length.setForeground(Color.BLACK);
		length.setText("Length: " + passLength);

		add = new JButton("+");
		add.addActionListener(this);
		add.setFont(medFont);
		add.setFocusable(false);
		add.setBorder(null);
		add.setBackground(FOREGROUND_COLOR);
		add.setForeground(Color.BLACK);
		add.setBounds(ADD_X, ADD_Y, ADD_WIDTH, ADD_HEIGHT);

		sub = new JButton("-");
		sub.addActionListener(this);
		sub.setFont(medFont);
		sub.setFocusable(false);
		sub.setBorder(null);
		sub.setBackground(FOREGROUND_COLOR);
		sub.setForeground(Color.BLACK);
		sub.setBounds(SUB_X, SUB_Y, SUB_WIDTH, SUB_HEIGHT);

		generate = new JButton("Generate");
		generate.addActionListener(this);
		generate.setFont(medFont);
		generate.setFocusable(false);
		generate.setBorder(null);
		generate.setBackground(FOREGROUND_COLOR);
		generate.setForeground(Color.BLACK);
		generate.setBounds(GEN_X, GEN_Y, GEN_WIDTH, GEN_HEIGHT);		

		frame.setIconImage(frameIcon.getImage());
		frame.add(text);
		frame.add(upperCase);
		frame.add(lowerCase);
		frame.add(numbers);
		frame.add(symbols);
		frame.add(length);
		frame.add(add);
		frame.add(sub);
		frame.add(generate);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add && passLength < 24) {
			passLength++;
			length.setText("Length: " + passLength);
		}
		if (e.getSource() == sub && passLength > 4) {
			passLength--;
			length.setText("Length: " + passLength);
		}
		if (e.getSource() == generate) {
			newPassword();
			text.setText(password);
		}
	}

	public void newPassword() {
		password = "";
		do {
			if (!upperCase.isSelected() &&
				!lowerCase.isSelected() &&
				!numbers.isSelected() &&
				!symbols.isSelected()) {
				password = "Enable a char type";
			} else {
				int charType = (int)(Math.random() * 4);
				if (charType == 0 && upperCase.isSelected()) {
					password = password + UPPERS[(int)(Math.random() * UPPERS.length)];
				} else if (charType == 1 && lowerCase.isSelected()) {
					password = password + LOWERS[(int)(Math.random() * LOWERS.length)];
				} else if (charType == 2 && numbers.isSelected()) {
					password = password + NUMS[(int)(Math.random() * NUMS.length)];
				} else if (charType == 3 && symbols.isSelected()) {
					password = password + SYMS[(int)(Math.random() * SYMS.length)];
				}
			}
		} while(password.length() < passLength);
	}

}

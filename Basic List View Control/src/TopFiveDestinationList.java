import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
	private static TopDestinationListFrame topDestinationListFrame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	topDestinationListFrame = new TopDestinationListFrame();
            	topDestinationListFrame.getContentPane().setBackground(Color.YELLOW);
            	topDestinationListFrame.setForeground(Color.MAGENTA);
            	topDestinationListFrame.setAlwaysOnTop(true);
            	topDestinationListFrame.setBackground(Color.RED);
                topDestinationListFrame.setTitle("Top 5 Destination List");
                
                JLabel lblNewLabel = new JLabel("Johnathon KInville");
                topDestinationListFrame.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);
                topDestinationListFrame.setVisible(true);
            }
        });
    }
	public TopDestinationListFrame getTopDestinationListFrame() {
		return topDestinationListFrame;
	}
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();

        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("<html>1. New York, NY  <br> One of the top tourist destinations. See all of the legendary sites!</html>", 
        		new ImageIcon(getClass().getResource("/resources/newyorkcity.jpg")));
        //Photo by: Javier Gil, Wikimedia commons
        addDestinationNameAndPicture("<html>2. Las Vegas, NV <br> Via Las vegas! Play some games or take in a show at the never ending party!</html> ", 
        		new ImageIcon(getClass().getResource("/resources/Las_vegas.jpg")));
        //Photo by: Thomas Wolf, www.foto-tw.de / Wikimedia Commons / CC BY-SA 3.0
        addDestinationNameAndPicture("<html>3. Tokyo, Japan <br> Come see one of the largest and most historic cities in the world!</html>", 
        		new ImageIcon(getClass().getResource("/resources/Tokyo.jpg")));
        //Photo by: laggnugg, Wikimedia commons
        addDestinationNameAndPicture("<html>4. Rome, Italy <br> Take in the history, the sights, and the food in Rome!", 
        		new ImageIcon(getClass().getResource("/resources/Rome.jpg")));
        //Photo by:  Markus Bernet, Wikimedia commons 
        addDestinationNameAndPicture("<html>5. Seattle, WA <br> Visit one of the coolest cities in the world!</html>", 
        		new ImageIcon(getClass().getResource("/resources/Seattle.jpg")));
        //Photo byL Baniel Schwen. Wikiemedia Commons
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}
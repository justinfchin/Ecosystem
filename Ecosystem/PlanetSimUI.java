/**
 * <h1> UI to Simulate Planet </h1>
 * <p>
 * This is the GUI to Simulate our Planet Class
 *
 * @author Justin Chin
 * @author Emmanuil Simkhayev
 * @version 211 Software Design Project
 * @since Dec. 26, 2016
 */

//import com.sun.tools.javac.comp.Flow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlanetSimUI extends JFrame {

    // private final JLabel introLabel;    //JLabel with text
    private final JTextField cycleField; //text field
    private final JButton cycleButton;  //button with just text
    private final JButton terminateButton; //button with just text
    // private JTextArea planetText;
    private Planet Earth;
    private final JTextPane textPane;
    private final JTextPane planetPane;
    private final JTextArea keyText;



    /**
     * Sets up the initial overview
     */
    public PlanetSimUI() {
        //Title
        super("Primitive Planet Ecosystem Simulation");

        //Layout
        setLayout(new BorderLayout());

        //Create ControlPanel
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(gridbag);

        //Label for the Intro
        JLabel introLabel = new JLabel("Welcome to your Ecosystem!");
        introLabel.setFont((new Font("Chalkduster", Font.BOLD, 16)));
        c.gridwidth = c.REMAINDER;        //Indicate end of the row.
        controlPanel.add(introLabel, c);

        //Button for NextCycle.
        cycleButton = new JButton("Next Cycle(s)");
        cycleButton.setToolTipText("Select this to run the number of cycles indicated to the left.");      //msg when mouse hovers
        controlPanel.add(cycleButton);

        //TextField for NextCycle.
        cycleField = new JTextField("1", 5);
        cycleField.setToolTipText("Insert the number of cycles you want run next.");
        c.anchor = c.WEST; //anchors this to a side
        controlPanel.add(cycleField, c);

        //Button for Termination.
        terminateButton = new JButton("Terminate");
        terminateButton.setToolTipText("Select this to end the program.");
        c.fill = c.HORIZONTAL;  //fills button across
        controlPanel.add(terminateButton, c);

        //Button Event Handling
        EventHandler handler = new EventHandler();
        terminateButton.addActionListener(handler);

        //Create the Border
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Controller"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        //Create Planet Panel
        planetPane = new JTextPane();
        planetPane.setEditable(false);  //make uneditable
        planetPane.setPreferredSize(new Dimension(250, 300));

        //Font Face & Size
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs, 16);
        StyleConstants.setFontFamily(attribs, "monospaced"); //makes char each same width
        planetPane.setParagraphAttributes(attribs, true);


        //Create the Border
        planetPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Planet"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        //Create the Key/Legend Panel
        keyText = new JTextArea();
        keyText.setFont((new Font("monospaced", Font.PLAIN, 12)));


        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new BorderLayout());

        keyPanel.add(keyText, BorderLayout.BEFORE_FIRST_LINE);


        keyPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Key / Legend"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        //Create Locations Pane
        textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane locationsPanel = new JScrollPane(textPane);
        locationsPanel.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        locationsPanel.setPreferredSize(new Dimension(250, 155));
        locationsPanel.setMinimumSize(new Dimension(10, 10));
        locationsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Object Locations"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        //Put everything together.
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(controlPanel, BorderLayout.PAGE_START);
        leftPanel.add(planetPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(keyPanel, BorderLayout.PAGE_START);
        rightPanel.add(locationsPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.LINE_START);
        add(rightPanel, BorderLayout.LINE_END);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    /**
     * Private Inner Class for Event Handling The Termination Button
     */
    private class EventHandler implements ActionListener {

        /**
         * Process Field Events
         *
         * @param event
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == terminateButton)
                System.exit(0);
        }
    }

    /**
     * ActionListener Button To determine Steps of CycleButton
     *
     * @param cycleButtonListener The Listen for the CycleButton
     */
    public void addListener(ActionListener cycleButtonListener) {
        cycleButton.addActionListener(cycleButtonListener);
    }

    /**
     * Sets the Text in the PlanetPane
     *
     * @param output String to be printed.
     */
    public void setPlanetPane(String output) {
        planetPane.setText(output);
    }

    /**
     * Sets the Text in the PlanetPane
     *
     * @param output String to be printed.
     */
    public void setTextPane(String output) {
        textPane.setText(output);
    }

    /**
     * Get Field in Cycle Field
     *
     * @return The value in the cycle Field.
     */
    public String getCycleField() {
        return cycleField.getText();
    }

    /**
     * Set the Text in the KyPane
     * @param output String to be printed.
     */
    public void setKeyText(String output){
        keyText.setText(output);
    }
}
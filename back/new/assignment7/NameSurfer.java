package com.shpp.p2p.cs.bkuzhel.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /**
     * Global value for JLabel nameLabel
     */
    private final JLabel nameLabel = new JLabel("Name: ");
    /**
     * Global value for JTextField nameField
     */
    private final JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
    /**
     * Global value for JButton graphButton
     */
    private final JButton graphButton = new JButton("Graph");
    /**
     * Global value for JButton clearButton
     */
    private final JButton clearButton = new JButton("Clear");
    /**
     * Global value for NameSurferDataBase dataBase
     */
    NameSurferDataBase dataBase;
    /**
     * Global value for NameSurferGraph graph
     */
    private NameSurferGraph graph;

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(nameLabel, NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        nameField.addActionListener(this);
        addActionListeners();
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph);
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == graphButton || e.getSource() == nameField) {
            String name = nameField.getText();
            if (dataBase.findEntry(name) != null) {
                graph.addEntry(dataBase.findEntry(name));
            } else {
                System.out.println("Name not found :(");
            }
            graph.update();
        } else if (e.getSource() == clearButton) {
            graph.clear();
            graph.update();
        }
    }
}

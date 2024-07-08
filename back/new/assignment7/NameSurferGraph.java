package com.shpp.p2p.cs.bkuzhel.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    int width = getWidth();
    int height = getHeight();
    int columnWidth = width / NDECADES;
    private ArrayList<NameSurferEntry> entries = new ArrayList<>();

    /**
     * The array of 5 colours for first 10 rows of bricks
     */
    private static final Color[] colorList = {
            Color.blue, Color.red, Color.magenta, Color.black};

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */

    public NameSurferGraph() {
        addComponentListener(this);
        // You fill in the rest //
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entries.clear();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGrid();
        drawEntries();
    }

    /**
     * For every entry in entries list, calculates coordinates of lines and labels
     * then draws it on canvas
     */
    private void drawEntries() {
        int realCanvasHeight = (height - 2 * GRAPH_MARGIN_SIZE);
        double ranksPerPixelRatio = (double) realCanvasHeight / MAX_RANK;
        for (NameSurferEntry entry : entries) {
            for (int i = 0; i < NDECADES - 1; i++) {
                // y-coordinate of top dot
                double y0 = entry.getRank(i) != 0 ? (entry.getRank(i) * ranksPerPixelRatio + GRAPH_MARGIN_SIZE) :
                        MAX_RANK * ranksPerPixelRatio + GRAPH_MARGIN_SIZE;
                // y-coordinate of bottom dot
                double y1 = entry.getRank(i + 1) != 0 ? (entry.getRank(i + 1) * ranksPerPixelRatio + GRAPH_MARGIN_SIZE) :
                        MAX_RANK * ranksPerPixelRatio + GRAPH_MARGIN_SIZE;
                GLine line = new GLine(i * columnWidth, y0, (i + 1) * columnWidth, y1);
                line.setColor(colorList[entries.indexOf(entry) % colorList.length]);
                add(line);
            }
            for (int i = 0; i < NDECADES; i++) {
                double y0 = entry.getRank(i) != 0 ? (entry.getRank(i) * ranksPerPixelRatio + GRAPH_MARGIN_SIZE) :
                        MAX_RANK * ranksPerPixelRatio + GRAPH_MARGIN_SIZE;
                String lbl = entry.getName() + " " + (entry.getRank(i) != 0 ? entry.getRank(i) : "*");
                GLabel label = new GLabel(lbl, i * columnWidth, y0);
                label.setColor(colorList[entries.indexOf(entry) % colorList.length]);
                add(label);
            }
        }

    }

    /**
     * A function that draws a grid on the canvas.
     */
    private void drawGrid() {
        width = getWidth();
        height = getHeight();
        columnWidth = width / NDECADES;
        //horizontal lines
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(i * columnWidth, 0, i * columnWidth, height));
            add(new GLabel(Integer.toString(START_DECADE + i * 10), i * columnWidth, height));
        }
        //vertical lines
        add(new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE));
        add(new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE));
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}

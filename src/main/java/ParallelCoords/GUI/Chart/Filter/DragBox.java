package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

public class DragBox extends JComponent {
    private final int width = 50;
    private final int height = 40;
    private final boolean upperSlider;
    private volatile int screenY = 0;
    private int xPos;
    private int yPos;
    private int outerBound;
    private Color color;
    private int thickness;

    public DragBox(int xInit, int yInit, int thickness, FilterSlider filter, boolean isUpperSlider) {
        //setBorder(border);
        setBackground(Color.BLACK);
        setBounds(0, 0, width, height);
        setOpaque(true);

        this.upperSlider = isUpperSlider;
        int segments = UserSettings.getInstance().getUserGraphSettings().getAxesPerScreenWidth();
        xPos = xInit - width/2;

        yPos = yInit - height;

        if (upperSlider) {
            outerBound = yPos;
        } else {
            yPos = yPos + height;
            outerBound = yPos;
        }

        setLocation(xPos, yPos);
        this.thickness = thickness;

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                updateCoords(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateCoords(e);
                filter.updateValues();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaY = e.getYOnScreen() - screenY;
                if (isUpperSlider) {
                    if ((yPos + deltaY) < (filter.getLowerY() - 1 - height) && (yPos + deltaY) >= outerBound) {
                        setLocation(xPos, yPos + deltaY);
                    } else {
                        if ((yPos + deltaY) >= (filter.getLowerY() - 1 - height)) {
                            setLocation(xPos, filter.getLowerY() - 1 - height);
                        }
                        if ((yPos + deltaY) < outerBound) {
                            setLocation(xPos, outerBound);
                        }
                    }
                } else {
                    if ((yPos + deltaY - height) > (filter.getUpperY() + 1) && (yPos + deltaY) <= outerBound) {
                        setLocation(xPos, yPos + deltaY);
                    } else {
                        if ((yPos + deltaY - height) <= (filter.getUpperY() + 1)) {
                            setLocation(xPos, filter.getUpperY() + 1 + height);
                        }
                        if ((yPos + deltaY) > outerBound) {
                            setLocation(xPos, outerBound);
                        }
                    }
                }
                filter.updateValues();
            }


            @Override
            public void mouseMoved(MouseEvent e) {
                filter.updateValues();
            }

        });
    }

    private void updateCoords(MouseEvent e) {
        screenY = e.getYOnScreen();

        xPos = getX();
        yPos = getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(thickness));
        int[] colourValues = UserSettings.getInstance().getUserGraphSettings().getFilterColour();
        g2.setColor(new Color(colourValues[0],colourValues[1],colourValues[2]));

        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(width / 2f - (width / 7f), 1, width / 3.5f, height - 2, 5, 5);
        g2.fill(roundRectangle2D);
        g2.setColor(Color.black);
        g2.draw(roundRectangle2D);
        if (upperSlider) {
            g2.setStroke(new BasicStroke(7f));
            g2.drawLine(width / 16 * 5, height, width / 16 * 11, height);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(width / 2 + 3, height - 25, width / 2, height -20);
            g2.drawLine(width / 2 - 3, height - 25, width / 2, height -20);
            g2.drawLine(width / 2 + 3, height - 17, width / 2, height -12);
            g2.drawLine(width / 2 - 3, height - 17, width / 2, height -12);

        } else {
            g2.setStroke(new BasicStroke(7f));
            g2.drawLine(width / 16 * 5, 0, width / 16 * 11, 0);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(width / 2 + 3, height - 20, width / 2, height -25);
            g2.drawLine(width / 2 - 3, height - 20, width / 2, height -25);
            g2.drawLine(width / 2 + 3, height - 12, width / 2, height -17);
            g2.drawLine(width / 2 - 3, height - 12, width / 2, height -17);

        }
    }

    public void resetPos() {
        setYPos(outerBound);
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos2) {
        this.xPos = xPos2 - 18;
        setLocation(xPos, yPos);
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
        setLocation(xPos, yPos);
    }

    public int getRealYPos() {
        if (upperSlider) {
            return yPos + height;
        } else {
            return yPos;
        }

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOuterBound(int bound) {
        this.outerBound = bound;
        if (yPos > outerBound) {
            yPos = outerBound;
            setLocation(xPos, yPos);
        }
    }


    public void setColour(Color color) {
        setBackground(color);
        this.repaint();
    }


    public void setNewBorder(LineBorder border) {
        this.setBorder(border);
    }
}
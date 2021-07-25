package ParallelCoords.GUI.Chart.Filter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

class DragBox extends JComponent {
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private int xPos;
    private int yPos;
    private final int width = 50;
    private final int height = 40;
    private final boolean upperSlider;
    private int outerBound;
    private Color color;
    private int thickness;

    public DragBox(int xInit, int yInit, Color color, int thickness, FilterSlider filter, boolean isUpperSlider) {
        //setBorder(border);
        setBackground(Color.BLACK);
        setBounds(0, 0, width, height);
        setOpaque(true);

        this.upperSlider = isUpperSlider;
        xPos = xInit - width/2;

        yPos = yInit - height;

        if(upperSlider){
            outerBound = yPos;
        }
        else {
            yPos = yPos + height;
            outerBound = yPos;
        }

        setLocation(xPos, yPos);
        //setSize(30,20);

        this.color = color;
        this.thickness = thickness;

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) { }

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
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println(filter.getUpperY() + "    " + filter.getLowerY());
                //int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;
                if(isUpperSlider){
                    if((yPos + deltaY) < (filter.getLowerY()-1) && (yPos + deltaY) >= outerBound) {
                        setLocation(xPos, yPos + deltaY);
                    }
                }
                else{
                    if((yPos + deltaY) > (filter.getUpperY()+1)  && (yPos + deltaY) <= outerBound) {
                        setLocation(xPos, yPos + deltaY);
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
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        xPos = getX();
        yPos = getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //g2.drawRect(0, 0, width, height);

        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(Color.BLACK);
        /*
        g2.drawLine(0,height/2, width, height/2);
        g2.setColor(color);
        g2.drawLine(width,0,width, height);
        g2.drawLine(0,0,0, height);
        g2.drawLine(width - (width/10), 0, width, 0);
        g2.drawLine(width - (width/10), height, width, height);
        g2.drawLine((width/10), 0, 0, 0);
        g2.drawLine((width/10), height, 0, height);
*/
        g2.setColor(new Color(100,100,100, 150));
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(width/2f - (width/7f),1,width/3.5,height-2, 5,5);
        g2.fill(roundRectangle2D);
        g2.setColor(Color.black);
        g2.draw(roundRectangle2D);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(width/2 -3,height/2 -5, width/2 +3 , height/2 -5 );
        g2.drawLine(width/2 -3,height/2 , width/2 +3 , height/2 );
        g2.drawLine(width/2 -3,height/2 +5, width/2 +3 , height/2 +5 );

    }

    public void resetPos(){
        setYPos(outerBound);
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getRealYPos() {
        if (upperSlider){
            return yPos + height;
        }
        else {
            return yPos;
        }

    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
        setLocation(xPos,yPos);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
        setLocation(xPos,yPos);
    }
    public void setOuterBound(int bound){
        this.outerBound = bound;
        if (yPos > outerBound){
            yPos = outerBound;
            setLocation(xPos,yPos);
        }
    }
    public void setColour(Color color){
        setBackground(color);
        this.repaint();
    }


    public void setNewBorder(LineBorder border){
        this.setBorder(border);
    }
}
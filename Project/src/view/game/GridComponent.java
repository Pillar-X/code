package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GridComponent extends JComponent {
    private int row;
    private int col;
    private final int id; // represents the units digit value. It cannot be changed during one game.
    private Image wallImage;
    private Image floorImage;
    private Image diamondImage;
    private Image doorImage;
    private Hero hero;
    private SecondHero secondHero;
    private Box box;
    static Color color = new Color(246, 246, 229);

    public GridComponent(int row, int col, int id, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.id = id;
        loadImages();
    }


    public void paintComponent(Graphics g) {
        super.printComponents(g);
        Color borderColor = color;
        switch (id % 10) {
            case 3:
                g.drawImage(floorImage, 0, 0, getWidth(), getHeight(), this);
                g.drawImage(doorImage, 0, 0, getWidth(), getHeight(), this);
                break;
            case 1:
                g.drawImage(wallImage, 0, 0, getWidth(), getHeight(), this);
                borderColor = Color.DARK_GRAY;
                break;
            case 0:
                g.drawImage(floorImage, 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                g.drawImage(floorImage, 0, 0, getWidth(), getHeight(), this);
                g.drawImage(diamondImage, 0, 0, getWidth(), getHeight(), this);
                break;
        }
        Border border = BorderFactory.createLineBorder(borderColor, 1);
        this.setBorder(border);
    }//绘制墙壁、地板、钻石图片，其中钻石图片代表终点
    private void loadImages() {
        try {
            doorImage = ImageIO.read(new File("PictureResource/door.png"));
            wallImage = ImageIO.read(new File("PictureResource/wall.png"));
            floorImage = ImageIO.read(new File("PictureResource/floor.png"));
            diamondImage = ImageIO.read(new File("PictureResource/diamond.png"));
            if (wallImage == null || floorImage == null || diamondImage == null || doorImage == null) {
                System.out.println("Failed to load images.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//加载图片
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getId() {
        return id;
    }

    //When adding a hero in this grid, invoking this method.
    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);
    }

    //When adding a box in this grid, invoking this method.
    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }
    public void setSecondHeroInGrid(SecondHero secondHero){
        this.secondHero = secondHero;
        this.add(secondHero);
    }
    //When removing hero from this grid, invoking this method
    public Hero removeHeroFromGrid() {
        this.remove(this.hero);//remove hero component from grid component
        Hero h = this.hero;
        this.hero = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return h;
    }
    public SecondHero removeSecondHeroFromGrid(){
        this.remove(this.secondHero);
        SecondHero sech = this.secondHero;
        this.secondHero = null;
        this.revalidate();
        this.repaint();
        return sech;
    }
    //When removing box from this grid, invoking this method
    public Box removeBoxFromGrid() {
        this.remove(this.box);//remove box component from grid component
        Box b = this.box;
        this.box = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return b;
    }
}

package Model;

import processing.core.PApplet;

public class Polo implements Runnable {

	private int x, y;
	private final float SIZE = 20;
	private String name;
    private int quantity;
    private int r, g, b;
    private Boolean delete;
    private Boolean newBall;
	private PApplet app;

	public Polo(int x, int y, String name, int r, int g, int b, boolean newBall, boolean delete,int quantity) {
			
			this.x = x;
			this.y = y;
	        this.r = r;
	        this.g = g;
	        this.b = b;
	        this.newBall = newBall;
	        this.delete = delete;
	        this.quantity = quantity;
	        this.name = name;
	}


	public void dibujar() {
		app.fill(0,0,255);
		//app.circle(posX, posY, SIZE);
	}

	public void mover() {
		//posX += app.random(-2, 2);
		//posY += app.random(-1, 1);

	}

	//public void run() {
		//mover();
	//}

//}




	public void run() {
		
	}
	public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getNewBall() {
        return newBall;
    }

    public void setNewBall(Boolean newBall) {
        this.newBall = newBall;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuant() {
        return quantity;
    }

    public void setQuant(int quantity) {
        this.quantity = quantity;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}


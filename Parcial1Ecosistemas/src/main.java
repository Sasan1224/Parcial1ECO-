import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import Model.Polo;
import processing.core.PApplet;

public class main extends PApplet {

	private ArrayList<Polo> Politos;

	private int x, y, r, g, b, quantity, mover;
	private String name;
	int floatx, floaty;
	private Socket socket;

	BufferedReader reader;
	BufferedWriter writer;

	InetAddress ipLocal;

	boolean delete, newBall, move;

	public static void main(String[] args) {
		PApplet.main("main");

	}

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		Politos = new ArrayList<Polo>();
		for (int i = 0; i < 20; i++) {
			float posX = random(300, 500);
			float posY = random(300,500);

			serverStart();
		}
	}

	public void draw() {
		background(255);
		for (int i = 0; i < Politos.size(); i++) {

			fill(Politos.get(i).getR(), Politos.get(i).getG(), Politos.get(i).getB());
			ellipse(floatx, floaty, 20, 20);

			floatx = Politos.get(i).getX() + mover;
			floaty = Politos.get(i).getY() + mover;

			showName();

			if (move == true) {
				movementPolos();
			} else {
				stopPolos();
			}

			if (delete == true) {
				Politos.clear();
			}
		}
	}

	public void serverStart() {

		new Thread(() -> {

			try {
				ipLocal = InetAddress.getLocalHost();
				System.out.println("Puerto en la ip " + ipLocal.getHostAddress());
				ServerSocket server = new ServerSocket(8080);
				System.out.println("Conectando");
				socket = server.accept();
				System.out.println("Se conectó");

	
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);

				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				writer = new BufferedWriter(osw);

				while (true) {
					System.out.println("Esperando mensaje");
					String line = reader.readLine();
					System.out.println("Listo" + line);

					Gson gson = new Gson();

					Polo Polito = gson.fromJson(line, Polo.class);

					for (int i = 0; i < Polito.getQuant(); i++) {

						x = Polito.getX();
						y = Polito.getY();

						r = Polito.getR();
						g = Polito.getG();
						b = Polito.getB();

						name = Polito.getName();

						quantity = Polito.getQuant();
						delete = Polito.getDelete();
						newBall = Polito.getNewBall();

						Politos.add(new Polo(x, y, name, quantity, r, g, newBall, delete, b));

					}

				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}).start();
	}

	public void movementPolos() {
		for (int i = 0; i < Politos.size(); i++) {

			mover = (int) random(0, 5);
			switch (mover) 
	        {
	            case 1:  mover = 1;
	            Politos.get(i).setX(Politos.get(i).getX() + 2);
	                     break;
	            case 2:  mover = 2;
	            Politos.get(i).setX(Politos.get(i).getX() - 2);
	                     break;
	            case 3:   mover = 3;
	            Politos.get(i).setY(Politos.get(i).getY() + 2);
	                     break;
	            case 4:   mover = 4;
	            Politos.get(i).setY(Politos.get(i).getY() - 2);
	                     break;
	            case 5:   mover = 5;
	            Politos.get(i).setY(Politos.get(i).getY() - 2);
	                     break;
	        }
		}
	rebotar();
	}
public void rebotar() {
	
	if (floatx >= 600) {
		floaty = 600;

	}

	else if (floatx <= 0) {
		floatx = 1;
	}

	if (floaty >= 600) {
		floaty = 599;
	}

	else if (floaty <= 0) {
		floaty = 1;
	}
	
}
	public void showName() {

		for (int i = 0; i < Politos.size(); i++) {
			if (dist(mouseX, mouseY, Politos.get(i).getX(), Politos.get(i).getY()) < 25) {
				fill(r, g, b);
				text(Politos.get(i).getName(), mouseX, mouseY);
				move = false;

			} else {
				move = true;
			}
		}
	}

	public void stopPolos() {

		for (int i = 0; i < Politos.size(); i++) {
			if (dist(mouseX, mouseY, Politos.get(i).getX(), Politos.get(i).getY()) < 25) {
				fill(r, g, b);
				text(Politos.get(i).getName(), mouseX, mouseY);
				move = false;

			} else {
				move = true;
			}
			Politos.get(i).setX(Politos.get(i).getX() + mover);
			Politos.get(i).setY(Politos.get(i).getY() + mover);
			}
		}
	}


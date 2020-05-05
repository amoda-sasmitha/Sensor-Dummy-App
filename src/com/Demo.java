package com;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import Util.RandomGen;
import model.Sensor;

public class Demo {

	public static final String URL  = "http://localhost:4000/sensors/updateall";  
	public static boolean danger = false;
	static GraphicsConfiguration gc;
	public static JLabel label;
	public static JButton danger_btn;
	static ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	
	public static void main(String[] args) {
		
		RandomGen random = new RandomGen(1, 5);
		Gson gson = new Gson();
		
		JFrame frame= new JFrame(gc);	
		frame.setTitle("Dummy Sensor Data Generator");
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.white);
		danger_btn = new JButton("Simulate Threat" );
		danger_btn.setBounds(20,30, 150,20);  
		
		danger_btn.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				      danger = true;
			        }  
			    });  
		label = new JLabel("loading...", SwingConstants.CENTER  );
		frame.add(danger_btn);
		frame.add(label);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	try {
		    		String data = gson.toJson(genarateData(random));
		    		System.out.println("Request : " + data);
					sendDataToAPI( URL  , data);
					updateLabel();
					
				} catch (Exception e) {
					System.out.println(e);
				} 
		    }
		}, 0, 5000);
		
			
		
	}
	
	public static void sendDataToAPI(String url , String body) throws ClientProtocolException, IOException {
		
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(url);
		StringEntity postingString = new StringEntity(body);
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse  response = httpClient.execute(post);
		
		HttpEntity entity = response.getEntity();

        String content = EntityUtils.toString(entity);
        System.out.println("Request : " + content +"\n");
	}
	
	public static ArrayList<Sensor> genarateData(RandomGen random){
		sensors.clear();
		sensors.add( new Sensor(3 , random.nextInt() , random.nextInt() ));
		if(danger == true) {
			sensors.add( new Sensor(4 , 9 , 5 ));
			danger = false;
		}else {
			sensors.add( new Sensor(4 , random.nextInt() , random.nextInt() ));
		}
		sensors.add( new Sensor(5 , random.nextInt() , random.nextInt() ));
		return sensors;
	}
	
	public static void updateLabel() {
		String text = "<HTML><h3>Current Sensors data will display</h3>";
		for (Sensor s : sensors) {
			text += "<h2>";
			text += "ID : " + s.getId() + "   SMOKE LEVEL : " + s.getSmoke_level() + "   CO2 LEVEL : " + s.getCo2_level();	
			text += "</h2>";
		}
		text += "</HTML>";
		label.setText(text);
	}

}

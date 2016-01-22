package finalproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapRepo {
	static MapRepo inst;
	
	MapMomento momento = null;
	
	public MapRepo()
	{
		 try
		 {
		    FileInputStream fileIn = new FileInputStream("map.ser");
		    ObjectInputStream in = new ObjectInputStream(fileIn);
		    momento = (MapMomento) in.readObject();
		    in.close();
		    fileIn.close();
		 } catch(IOException i) {
		    System.out.println("No save.");
		 } catch(ClassNotFoundException c) {
		    System.out.println("Employee class not found");
		    c.printStackTrace();
		 }
	}
	
	public static MapRepo getInst()
	{
		if(inst != null)
		{
			return inst;
		}
		inst = new MapRepo();
		return inst;
	}
	
	public void setState(MapMomento mm)
	{
		this.momento = mm;
	}
	
	public MapMomento getState()
	{
		return momento;
	}
	
	public void saveState()
	{
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("map.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(momento);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized map data is saved in map.ser");
	      }catch(IOException i){
	          i.printStackTrace();
	      }
	}
}

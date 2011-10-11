package me.tehbeard.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.util.Vector;
/**
 * Represents a cuboid of space
 * @author James
 *
 */
public class Cuboid {

	Vector v1,v2=null;
	String world = null;
	
	public String getWorld(){
		return world;
	}


	

	public Cuboid(){
		
	}
	
	public void setCuboid(String line){
		String[] l = line.split(":");
		world=l[0];
		v1 = new Vector(
				Integer.parseInt(l[1]), 
				Integer.parseInt(l[2]), 
				Integer.parseInt(l[3]));
		v2 = new Vector(
				Integer.parseInt(l[4]), 
				Integer.parseInt(l[5]), 
				Integer.parseInt(l[6]));
		
		
		
	}
	
	public void setCuboid(Vector c1,Vector c2,String world){
		v1=c1;
		v2=c2;
		this.world = world;
	}
	
	/**
	 * is a location inside this cuboid
	 * @param l
	 * @return true if inside
	 */
	public boolean isInside(Location l){
		return (l.toVector().isInAABB(Vector.getMinimum(v1, v2),Vector.getMaximum(v1, v2)) &&
				l.getWorld().getName().equals(world));
		
	}
	
	
	public ArrayList<String> getChunks(){
		ArrayList<String> chunks = new ArrayList<String>();
		int cx1 = v1.getBlockX()/16;
		int cz1 = v1.getBlockZ()/16;
		int cx2 = v2.getBlockX()/16;
		int cz2 = v2.getBlockZ()/16;
		int cx,cz;
		
		for(cx=cx1;cx<=cx2;cx++){
			for(cz=cz1;cz<=cz2;cz++){
				chunks.add(""+world+","+cx+","+cz);
			}
		}
		return chunks;
		
		
	}
	
	public Vector[] getCorners(){
		Vector[] c = new Vector[2];
		c[0]=v1;
		c[1]=v2;
		return c;
	}
}
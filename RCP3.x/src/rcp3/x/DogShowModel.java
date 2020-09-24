package rcp3.x;

import java.io.Serializable;
import java.util.Arrays;

public class DogShowModel implements Serializable{
	
	String dogName;
	String dogBreed;
	String ownerName;
	String Phno;
	String[] categories;
	
	
	
	public DogShowModel() {
		super();
	}



	public DogShowModel(String dogName, String dogBreed, String ownerName, String phno, String[] categories) {
		super();
		this.dogName = dogName;
		this.dogBreed = dogBreed;
		this.ownerName = ownerName;
		Phno = phno;
		this.categories = categories;
	}



	public String getDogName() {
		return dogName;
	}



	public void setDogName(String dogName) {
		this.dogName = dogName;
	}



	public String getDogBreed() {
		return dogBreed;
	}



	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}



	public String getOwnerName() {
		return ownerName;
	}



	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}



	public String getPhno() {
		return Phno;
	}



	public void setPhno(String phno) {
		Phno = phno;
	}



	public String[] getCategories() {
		return categories;
	}



	public void setCategories(String[] categories) {
		this.categories = categories;
	}



	@Override
	public String toString() {
		return "DogShowModel [dogName=" + dogName + ", dogBreed=" + dogBreed + ", ownerName=" + ownerName + ", Phno="
				+ Phno + ", categories=" + Arrays.toString(categories) + "]";
	}



	
	

}

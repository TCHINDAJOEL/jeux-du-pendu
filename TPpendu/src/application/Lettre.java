package application;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Objects;

public class Lettre {

	protected char lettre;
	protected ArrayList<Integer> position;
	
	public Lettre(char l,String mot)
	{
		lettre = l;
		position = new ArrayList<Integer>();
		for (int i = 0; i < mot.length(); i++) {
			if(mot.charAt(i) == l)
			{
				position.add(i);
			}	
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(lettre, position);
	}

	public char getLettre() {
		return lettre;
	}

	public ArrayList<Integer> getPosition() {
		return position;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	public ArrayList<Integer> getPositions() {
		return position;
	}

	public void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lettre other = (Lettre) obj;
		return lettre == other.lettre && Objects.equals(position, other.position);
	}
}

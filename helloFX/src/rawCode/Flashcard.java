package rawCode;
public class Flashcard {
private String front;
private String back;
private int difficulty=0;

public Flashcard(String front, String back) {
	this.front=front;
	this.back=back;
}

public String getFront() {return front;}
public String getBack() {return back;}
public int getDifficulty() {return difficulty;}

public void setFront(String front) {this.front = front;}
public void setBack(String back) {this.back = back;}
public void setDifficulty(int difficulty) {this.difficulty=difficulty;}

}
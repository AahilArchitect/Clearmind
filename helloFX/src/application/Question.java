package application;
public class Question {

private String question;
private String answer;
private int difficulty=0;
private String type;

public Question (String question, String answer, String type){
	this.question = question;
	this.answer = answer;
	this.type = type;
}

public String getQuestion() {
    return question;
}
public String getAnswer() {
return answer;
}

public void setDifficulty(int difficulty) {
    this.difficulty=difficulty;
}
public int getDifficulty() {
    return difficulty;
}

public String getType() {
    return type;
}



}

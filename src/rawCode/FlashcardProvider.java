package rawCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class FlashcardProvider {

    private Queue<Flashcard> cardQueue = new LinkedList<>();
    private Flashcard currentCard;

    public FlashcardProvider(ArrayList<Flashcard> cards) {

        ArrayList<Flashcard> copy = new ArrayList<>(cards);
        Collections.shuffle(copy);

        cardQueue.addAll(copy);
    }

    public Flashcard getCard() {

        if (cardQueue.isEmpty()) {
            currentCard = null;
            return null;
        }

        currentCard = cardQueue.poll();
        return currentCard;
    }

    public String getFront() {

        if (currentCard == null) {
            return null;
        }

        return currentCard.getFront();
    }

    public String getBack() {

        if (currentCard == null) {
            return null;
        }

        return currentCard.getBack();
    }

    public void knewCard() {

        if (currentCard == null) {
            return;
        }

        currentCard.setDifficulty(
            Math.max(0, currentCard.getDifficulty() - 1)
        );
    }

    public void didNotKnowCard() {

        if (currentCard == null) {
            return;
        }

        currentCard.setDifficulty(
            currentCard.getDifficulty() + 1
        );

        cardQueue.add(currentCard);
    }

    public boolean hasNext() {
        return !cardQueue.isEmpty();
    }
}
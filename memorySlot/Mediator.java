package memorySlot;
import memorySlot.trump.TrumpModel;

public interface Mediator {
    public abstract void clearBetChip();
    public abstract void restoreScore();
    public abstract void removeScore(int clickedChip);
    public abstract void happenCardEvent(TrumpModel trump0, TrumpModel trump1, String trumpCombination);
    public abstract void hitSlot(int slotMark);
}
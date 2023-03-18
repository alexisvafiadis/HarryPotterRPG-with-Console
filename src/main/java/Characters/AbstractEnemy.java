package Characters;

public abstract class AbstractEnemy extends Character {
    int attackDelay;

    public abstract void act();

    public int getAttackDelay() {
        return attackDelay;
    }

    public void setAttackDelay(int attackDelay) {
        this.attackDelay = attackDelay;
    }
}

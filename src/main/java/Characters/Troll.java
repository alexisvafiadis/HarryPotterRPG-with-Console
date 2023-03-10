package Characters;

import Extra.Item;
import Game.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Troll extends Boss{
    boolean alive;
    Game game;
    float ATTACK_DAMAGE = 2;
    final ScheduledExecutorService executorService;
    double positionX;
    double positionY;
    double positionZ;
    final double MAX_HP = 300;

    public Troll(Game game, double positionX, double positionY, double positionZ) {
        this.game = game;
        spawn(positionX, positionY, positionZ);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                act();
            }
        }, 0, 4, TimeUnit.SECONDS);
    }

    @Override
    public double getMAX_HP() {
        return MAX_HP;
    }

    public void act() {
        System.out.println(HP);
        System.out.println(game.getPlayer().getHP());
        System.out.println(isAlive());
       if (!isAlive()) {
           executorService.shutdown();
       }
       else {
            attack(game.getPlayer());
        }
    }


    @Override
    public void attack(Character victim) {
        victim.damage(ATTACK_DAMAGE);
        System.out.println("You have been attacked by the troll");
    }

    public boolean getsHitBy(Item item) {
        return (item.getPositionX() == positionX && item.getPositionY() == positionY && item.getPositionZ() == positionZ);
    }
}

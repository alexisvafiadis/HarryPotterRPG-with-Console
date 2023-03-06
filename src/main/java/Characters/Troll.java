package Characters;

import Game.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Troll extends Boss{
    boolean alive;
    Game game;
    float ATTACK_DAMAGE;
    final ScheduledExecutorService executorService;

    public Troll(Game game) {
        this.game = game;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                act();
            }
        }, 0, 4, TimeUnit.SECONDS);
    }

    public void act() {
       if (!alive) {
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
}

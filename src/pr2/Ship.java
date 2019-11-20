package pr2;

public abstract class Ship extends GameObject {
    private int life;
    public Ship(Game game, int x, int y, int life)
    {
        super(game, x, y, life);
        life = life;
    }


    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {

    }

    @Override
    public boolean receiveMissileAttack(int damage) { //Preguntar si esta bien implementar aqui recieveMissileAttack
        boolean ok;
        if(life > 0){
            life = life - damage;
            ok = true;
        }
        else{
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean receiveShockWaveAttack(int damage) {
        boolean ok;
        if(life > 0){
            life = life - damage;
            ok = true;
        }
        else{
            ok = false;
        }
        return ok;

    }
}

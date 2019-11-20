package pr2;

public abstract class Weapon extends GameObject {
    private Object Bomb;

    public Weapon(Game game, int x, int y, int live) {
        super(game, x, y, live);
    }


    @Override
    public boolean receiveMissileAttack(int damage) { //Preguntar si esta bien implementar aqui recieveMissileAttack y poner un atributo life
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

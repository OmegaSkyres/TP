package pr2;

public abstract class EnemyShip extends Ship {
    private int points; //TODO MIRAR SI ESTA ATRIBUTO ES PRIVADO O PUBLICO YA QUE EN EL GAME TIENES UN METODO RECIVE POINTS
    public EnemyShip(Game game, int x, int y, int live) {
        super(game, x, y, live);
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

    @Override
    public boolean receiveMissileAttack(int damage) {
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

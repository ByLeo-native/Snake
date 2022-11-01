package logica.direccion;

public enum Direccion {
    ABAJO(0, 1), DERECHA(1, 0), ARRIBA(0, -1), IZQUIERDA(-1, 0);

    private final int direccionEnX;
    private final int direccionEnY;

    private Direccion(int direccionEnX, int direccionEnY) {
        this.direccionEnX = direccionEnX;
        this.direccionEnY = direccionEnY;
    }

    public int getDireccionEnX() {
        return direccionEnX;
    }

    public int getDireccionEnY() {
    	return direccionEnY;
    }
}


package logica.direccion;

public enum Direccion {
    ABAJO(1, '↓'), DERECHA(2, '→'), ARRIBA(3, '↑'), IZQUIERDA(4, '←');

    private final int itDireccion;
    private final char caracterRepresentacion;

    private Direccion(int itDireccion, char caracterRepresentacion) {
        this.itDireccion = itDireccion;
        this.caracterRepresentacion = caracterRepresentacion;
    }

    public static Direccion valueOfNumber(int itDireccion) {
        for (Direccion direccion : Direccion.values()) {
            if (direccion.getItDireccion() == itDireccion) {
                return direccion;
            }
        }
        throw new IllegalArgumentException("Valor " + itDireccion + " no encontrado para enum Direccion");
    }

    public int getItDireccion() {
        return itDireccion;
    }

    public char getCaracterRepresentacion() {
        return caracterRepresentacion;
    }

    /**
     * @param itDireccionBase Direccion base para la rotacion.
     * @param itRotacion: numero de veces que rota la dirección en sentido
     * inverso a las manecilas del reloj. Ejemplo: <br>
     *
     * itDireccionBase, itRotacion, itDireccionResultante<br>
     * Abajo↓, 1, Derecha→<br>
     * Abajo↓, 2, Arriba↑<br>
     * Abajo↓, 3, Izquierda←<br>
     *
     * Derecha→, 1, Arriba↑<br>
     * Derecha→, 2, Izquierda←<br>
     * @return La nueva Direccion
     */
    public static int getDireccionInversaRelativa(int itDireccionBase, int itRotacion) {
        int tmpDireccion = itDireccionBase;
        tmpDireccion += itRotacion;
        if (tmpDireccion > Direccion.values().length) {
            tmpDireccion -= Direccion.values().length;
        }
        return tmpDireccion;
    }

    public static int getDireccionInversaRelativa(int itDireccionBase) {
        return getDireccionInversaRelativa(itDireccionBase, 1);
    }

    /**
     * @param itDireccionBase Direccion base para la rotación.
     * @param itRotacion: numero de veces que rota la dirección en sentido a las
     * manecilas del reloj. Ejemplo: <br>
     *
     * itDireccionBase, itRotacion, itDireccionResultante<br>
     * Abajo↓, 1, Izquierda←<br>
     * Abajo↓, 2, Arriba↑<br>
     * Abajo↓, 3, Derecha→<br>
     *
     * Derecha→, 1, Abajo↓<br>
     * Derecha→, 2, Izquierda←<br>
     * @return La nueva Direccion
     */
    public static int getDireccionNormalRelativa(int itDireccionBase, int itRotacion) {
        int tmpDireccion = itDireccionBase;
        tmpDireccion -= itRotacion;
        if (tmpDireccion < 1) {
            tmpDireccion = Direccion.values().length - Math.abs(tmpDireccion);
        }
        return tmpDireccion;
    }

    public static int getDireccionNormalRelativa(int itDireccionBase) {
        return getDireccionNormalRelativa(itDireccionBase, 1);
    }

    public static int getDireccionRelativa(int itDireccionBase, int itRotacion) {
        if (itRotacion >= 0) {
            return getDireccionInversaRelativa(itDireccionBase, itRotacion);
        } else {
            return getDireccionNormalRelativa(itDireccionBase, Math.abs(itRotacion));
        }
    }

}


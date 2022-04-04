package bstorm.akimts.correction_jpa.exception;

//RuntimeException : uncheck
//Exception : check

public class ElementNotFoundException extends RuntimeException {
    private final Object id;
    private final Class<?> clazz;

    public ElementNotFoundException(Object id, Class<?> clazz) {
        super("L'élément d'id {" + id + "} n'a pas été trouvé !");
        this.id = id;
        this.clazz = clazz;
    }

    public Object getId() {
        return id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

}

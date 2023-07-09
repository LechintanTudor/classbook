package xyz.lechi.classbook.exception;

public class EntityNotFound extends RuntimeException {
    private final Class<?> entityClass;
    private final Long entityId;

    public EntityNotFound(Class<?> entityClass, Long entityId) {
        this.entityClass = entityClass;
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format(
            "No entity of type '%s' with id '%d' was found",
            entityClass.getSimpleName(),
            entityId
        );
    }
}

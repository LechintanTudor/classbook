package xyz.lechi.classbook.exception;

public class EntityNotFound extends RuntimeException {
    private final Class<?> entityClass;
    private final Long entityId;

    public EntityNotFound(Class<?> entityClass) {
        this.entityClass = entityClass;
        this.entityId = null;
    }

    public EntityNotFound(Class<?> entityClass, Long entityId) {
        this.entityClass = entityClass;
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        if (entityId != null) {
            return String.format(
                "No entity of type '%s' with id '%d' was found",
                entityClass.getSimpleName(),
                entityId
            );
        } else {
            return String.format(
                "Failed to find entity of type '%s'",
                entityClass.getSimpleName()
            );
        }
    }
}

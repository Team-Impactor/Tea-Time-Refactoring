package Server.TeaTimeProjectRefactoring.entity.constant;

import lombok.Getter;

public enum Role {
    USER, ADMIN, COUNSELOR;

    public static Role from(String role) {
        return Role.valueOf(role);
    }
}

package Server.TeaTimeProjectRefactoring.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PageInfo {

    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
}

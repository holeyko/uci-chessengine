package com.holeyko.chess.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GoOperationParameters {
    private Integer depth;
    private Integer nodes;
    private Integer mate;
    private Integer moveTime;
    private Boolean infinite;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (depth != null) {
            result.append("depth ").append(depth).append(" ");
        }
        if (nodes != null) {
            result.append("nodes ").append(nodes).append(" ");
        }
        if (mate != null) {
            result.append("mate ").append(mate).append(" ");
        }
        if (moveTime != null) {
            result.append("movetime ").append(moveTime).append(" ");
        }
        if (infinite != null && infinite) {
            result.append("infinite ").append(" ");
        }

        return result.toString();
    }
}

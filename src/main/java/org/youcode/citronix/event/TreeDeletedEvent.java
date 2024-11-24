package org.youcode.citronix.event;

import java.util.UUID;

public class TreeDeletedEvent {

    private final UUID treeId;

    public TreeDeletedEvent(UUID treeId) {
        this.treeId = treeId;
    }

    public UUID getTreeId() {
        return treeId;
    }
}

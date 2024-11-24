package org.youcode.citronix.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.youcode.citronix.event.TreeDeletedEvent;
import org.youcode.citronix.service.HarvestService;

@Component
@AllArgsConstructor
public class TreeDeletedEventListener {
    private final HarvestService harvestService;

    @EventListener
    public void handleTreeDeletedEvent(TreeDeletedEvent event) {
        harvestService.deleteAllHarvestDetailsByTreeId(event.getTreeId());
    }
}

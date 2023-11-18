package org.arn.hdsscapture.utils;

import org.arn.hdsscapture.entity.Locationhierarchy;

public class HierarchyUpdate {

	public enum Action {
        UPDATE,
        DELETE
    }

    private Action action;
    private Locationhierarchy data;

    public HierarchyUpdate () {}

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Locationhierarchy getData() {
        return data;
    }

    public void setData(Locationhierarchy data) {
        this.data = data;
    }
}

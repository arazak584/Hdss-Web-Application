package org.arn.hdsscapture.odk;

public class OdkUpdate {

	public enum Action {
        UPDATE,
        DELETE
    }

    private Action action;
    private ODK data;

    public OdkUpdate () {}

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public ODK getData() {
        return data;
    }

    public void setData(ODK data) {
        this.data = data;
    }
}

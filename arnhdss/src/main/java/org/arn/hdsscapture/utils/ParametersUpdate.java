package org.arn.hdsscapture.utils;

import org.arn.hdsscapture.entity.Settings;

public class ParametersUpdate {

	public enum Action {
        UPDATE,
        DELETE
    }

    private Action action;
    private Settings data;

    public ParametersUpdate () {}

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Settings getData() {
        return data;
    }

    public void setData(Settings data) {
        this.data = data;
    }
}
